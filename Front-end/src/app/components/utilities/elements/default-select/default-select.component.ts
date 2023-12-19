import { AfterViewInit, Component, Input, forwardRef } from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';
import { ControlValueAccessorDirective } from 'src/app/directives/control-value-accessor/control-value-accessor.directive';
import { Select, initTE } from 'tw-elements';

@Component({
    selector: 'app-default-select',
    templateUrl: './default-select.component.html',
    styleUrls: ['./default-select.component.css'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => DefaultSelectComponent),
            multi: true,
        },
    ],
})
export class DefaultSelectComponent<T>
    extends ControlValueAccessorDirective<T>
    implements AfterViewInit
{
    @Input() selectId!: string;
    @Input() label!: string;
    @Input() options!: Record<string | number, string | number>;
    @Input() errors: Record<string, string> = {};

    ngAfterViewInit(): void {
        initTE({ Select }, { allowReinits: true });
    }
}
