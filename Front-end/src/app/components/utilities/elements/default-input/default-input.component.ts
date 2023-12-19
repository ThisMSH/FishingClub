import {
    AfterViewInit,
    Component,
    ElementRef,
    Input,
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
    Input as TwInput,
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
    @Input() inputId: string = '';
    @Input() label: string = '';
    @Input() inputType: InputType = 'text';
    @Input() specialType!: SpecialType;
    @Input() maxLength: number | undefined;
    @Input() disablePastDate!: boolean;
    @Input() step!: number;
    @Input() errors: Record<string, string> = {};
    @Input() dateRules: any = {};

    ngAfterViewInit(): void {
        initTE({ TwInput }, { allowReinits: true });

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
