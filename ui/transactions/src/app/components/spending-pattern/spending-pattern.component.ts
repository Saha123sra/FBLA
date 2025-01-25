import { Component, OnInit } from '@angular/core';
import { SpendingPatternService } from 'src/app/services/spending-pattern.service';

@Component({
  selector: 'app-spending-pattern',
  templateUrl: './spending-pattern.component.html',
  styleUrls: ['./spending-pattern.component.css']
})

export class SpendingPatternComponent implements OnInit {
  spendingPattern: any;

  constructor(private spendingPatternService: SpendingPatternService) {}

  ngOnInit() {
    this.getSpendingPattern();
  }

  getSpendingPattern() {
    this.spendingPatternService.getSpendingPattern().subscribe(
      data => {
        this.spendingPattern = data;
      },
      error => {
        console.error('Error fetching spending pattern', error);
      }
    );
  }
}
