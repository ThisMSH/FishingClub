import { DOCUMENT } from '@angular/common';
import { Component, Inject, Renderer2 } from '@angular/core';
import { DarkInfo } from 'src/app/types/types';

@Component({
    selector: 'app-dark-mode',
    templateUrl: './dark-mode.component.html',
    styleUrls: ['./dark-mode.component.css'],
})
export class DarkModeComponent {
    darkInfo: DarkInfo = localStorage.getItem('dark-mode')
        ? JSON.parse(localStorage.getItem('dark-mode') as string)
        : { isDark: false };

    constructor(
        @Inject(DOCUMENT) private doc: Document,
        private renderer: Renderer2
    ) {}

    toggleDarkMode(): void {
        if (this.doc.documentElement.classList.contains('dark')) {
            this.darkInfo = { isDark: false };

            this.renderer.removeClass(this.doc.documentElement, 'dark');
            localStorage.setItem('dark-mode', JSON.stringify(this.darkInfo));
        } else {
            this.darkInfo = { isDark: true };

            this.renderer.addClass(this.doc.documentElement, 'dark');
            localStorage.setItem('dark-mode', JSON.stringify(this.darkInfo));
        }
    }

    ngOnInit(): void {
        if (!!this.darkInfo.isDark) {
            this.renderer.addClass(this.doc.documentElement, 'dark');
        }
    }
}
