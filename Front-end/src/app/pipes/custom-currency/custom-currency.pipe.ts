import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'customCurrency',
})
export class CustomCurrencyPipe implements PipeTransform {
    transform(value: string | number, currency: string): string {
        if (typeof value === "number") {
            return (Math.round(value * 100) / 100) + " " + currency;
        }

        return (Math.round(parseFloat(value) * 100) / 100) + " " + currency;
    }
}
