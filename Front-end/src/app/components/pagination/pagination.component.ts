import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';

@Component({
    selector: 'app-pagination',
    templateUrl: './pagination.component.html',
    styleUrls: ['./pagination.component.css'],
})
export class PaginationComponent implements OnChanges {
    @Input() pagesCount: number = 0;
    @Input() currentPage!: number;
    @Input() sizeLabel!: string;
    @Input() sizeId!: string;
    @Input() sizeDisabled!: boolean;
    @Input() sizeOptions: Record<number | string, number | string> = {};
    @Input() size!: string | number;
    @Output() emitPage = new EventEmitter();
    @Output() sizeChange = new EventEmitter();
    pages!: number[];

    goToPage(p: number): void {
        this.emitPage.emit({p});
    }

    pageSize(size: number | string): void {
        this.sizeChange.emit({size});
    }

    ngOnChanges(changes: SimpleChanges): void {
        if (changes['pagesCount']) {
            this.pages = Array.from(Array(this.pagesCount).keys());
        }
    }
}
