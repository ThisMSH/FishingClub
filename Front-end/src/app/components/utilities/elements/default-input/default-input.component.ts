import {
    AfterViewInit,
    Component,
    ElementRef,
    Input as NgInput,
    ViewChild,
    forwardRef,
} from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';
import { ControlValueAccessorDirective } from 'src/app/directives/control-value-accessor/control-value-accessor.directive';
import { InputType, SpecialType } from 'src/app/types/types';
import {
    Datepicker,
    Datetimepicker,
    Timepicker,
    Input,
    initTE,
} from 'tw-elements';
@Component({
    selector: 'app-default-input',
    templateUrl: './default-input.component.html',
    styleUrls: ['./default-input.component.css'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => DefaultInputComponent),
            multi: true,
        },
    ],
})
export class DefaultInputComponent<T>
    extends ControlValueAccessorDirective<T>
    implements AfterViewInit
{
    @ViewChild('inputContainer') inputContainer!: ElementRef;
    @NgInput() inputId: string = '';
    @NgInput() label: string = '';
    @NgInput() inputType: InputType = 'text';
    @NgInput() specialType!: SpecialType;
    @NgInput() maxLength: number | undefined;
    @NgInput() max!: number;
    @NgInput() min!: number;
    @NgInput() disablePastDate!: boolean;
    @NgInput() step!: number;
    @NgInput() errors: Record<string, string> = {};
    @NgInput() dateRules: any = {};

    ngAfterViewInit(): void {
        initTE({ Input }, { allowReinits: true });

        if (this.inputContainer && this.specialType === 'date') {
            new Datepicker(this.inputContainer.nativeElement, {
                ...this.dateRules,
                format: 'yyyy-mm-dd',
            });

            this.inputContainer.nativeElement.setAttribute(
                'data-te-datepicker-init',
                ''
            );
        } else if (this.inputContainer && this.specialType === 'datetime') {
            new Datetimepicker(this.inputContainer.nativeElement, {
                timepicker: { ...this.dateRules.timepicker, format24: true },
                datepicker: {
                    ...this.dateRules.datepicker,
                    format: 'yyyy-mm-dd',
                },
            });

            this.inputContainer.nativeElement.setAttribute(
                'data-te-date-timepicker-init',
                ''
            );
        } else if (this.inputContainer && this.specialType === 'time') {
            new Timepicker(this.inputContainer.nativeElement, {
                ...this.dateRules,
                format24: true,
            });

            this.inputContainer.nativeElement.setAttribute(
                'data-te-timepicker-init',
                ''
            );
        }
    }
}
