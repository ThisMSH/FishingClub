import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
    selector: 'app-pagination',
    templateUrl: './pagination.component.html',
    styleUrls: ['./pagination.component.css'],
})
export class PaginationComponent implements OnInit {
    @Input() pagesCount: number = 0;
    @Input() currentPage!: number;
    @Output() emitPage = new EventEmitter();
    pages!: number[];

    goToPage(p: number): void {
        this.emitPage.emit({p});
    }

    ngOnInit(): void {
        this.pages = Array.from(Array(this.pagesCount).keys());
    }
}
