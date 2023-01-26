import { Component, OnInit } from '@angular/core';
import { categorie } from '../categories';
import { dashboardService } from './dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private dashboardService: dashboardService) { }

  listeCategories: categorie[] = [];

  ngOnInit(): void {
    this.listeCategories = this.dashboardService.getDashboard();
  }


}
