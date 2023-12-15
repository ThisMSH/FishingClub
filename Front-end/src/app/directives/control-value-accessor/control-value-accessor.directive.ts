import { Directive, Inject, Injector, OnInit } from '@angular/core';
import {
    ControlValueAccessor,
    FormControl,
    NgControl,
    FormControlName,
    FormGroupDirective,
    FormControlDirective,
    Validators,
} from '@angular/forms';
import { Subject, takeUntil, startWith, distinctUntilChanged, tap } from 'rxjs';

@Directive({
    selector: '[appControlValueAccessor]',
})
export class ControlValueAccessorDirective<T>
    implements OnInit, ControlValueAccessor
{
    control: FormControl = new FormControl();
    isRequired: boolean = false;
    isDisabled: boolean = false;

    private destroy$ = new Subject<void>();
    private onTouched!: () => T;

    constructor(@Inject(Injector) private injector: Injector) {}

    writeValue(val: T): void {
        this.control
            ? this.control.setValue(val)
            : (this.control = new FormControl(val));
    }

    registerOnChange(fn: (val: T | null) => T): void {
        this.control?.valueChanges
            .pipe(
                takeUntil(this.destroy$),
                startWith(this.control.value),
                distinctUntilChanged(),
                tap((val) => fn(val))
            )
            .subscribe(() => this.control?.markAsUntouched());
    }

    registerOnTouched(fn: () => T): void {
        this.onTouched = fn;
    }

    setDisabledState?(isDisabled: boolean): void {
        this.isDisabled = isDisabled;
    }

    setFormControl(): void {
        try {
            const formControl = this.injector.get(NgControl);

            switch (formControl.constructor) {
                case FormControlName:
                    this.control = this.injector
                        .get(FormGroupDirective)
                        .getControl(formControl as FormControlName);
                    break;
                default:
                    this.control = (formControl as FormControlDirective)
                        .form as FormControl;
                    break;
            }
        } catch (err) {
            console.log('Error in the control value accessor: ' + err);
            this.control = new FormControl();
        }
    }

    ngOnInit(): void {
        this.setFormControl();
        this.isRequired =
            this.control?.hasValidator(Validators.required) ?? false;
    }
}
