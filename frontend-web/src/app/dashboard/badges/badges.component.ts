import { DialogDeleteBadgeComponent } from './../../dialogs/dialog-delete-badge/dialog-delete-badge.component';
import { BadgeService } from './../../services/badge.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatDialog, MatTableDataSource } from '@angular/material';
import { Title } from '@angular/platform-browser';
import { DialogBadgeComponent } from 'src/app/dialogs/dialog-badge/dialog-badge.component';
import { OneBadgeResponse } from 'src/app/interfaces/one-badge-response';

@Component({
  selector: 'app-badges',
  templateUrl: './badges.component.html',
  styleUrls: ['./badges.component.scss']
})
export class BadgesComponent implements OnInit {
  displayedColumns: string[] = ['icon', 'name', 'points', 'description', 'pois', 'actions'];
  dataSource;
  badges: any[] = [];
  alertMsg: string;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  // tslint:disable-next-line:max-line-length
  constructor(private titleService: Title, private snackBar: MatSnackBar, private badgeService: BadgeService, private router: Router, public dialog: MatDialog) { }

  ngOnInit() {
    this.titleService.setTitle('Badges');
    this.getAllBadges('Success retrieving items.');
  }

  getAllBadges(message: string) {
    this.badgeService.getAll().subscribe(receivedBadges => {
      this.dataSource = new MatTableDataSource(receivedBadges.rows);
      this.dataSource.paginator = this.paginator;
    }, error => {
      this.snackBar.open('There was an error loading the data.', 'Close', {duration: 3000});
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDialogNewBadge() {
    const newBadgeDialog = this.dialog.open(DialogBadgeComponent, {panelClass: 'add-dialog'});

    newBadgeDialog.afterClosed().subscribe(result => {
      if (result === 'confirm') {
        this.alertMsg = 'Badge created';
        this.getAllBadges(this.alertMsg);
      }
    });
  }

  openDialogDeleteBadge(badge: OneBadgeResponse) {
    // tslint:disable-next-line:max-line-length
    const deleteBadgeDialog = this.dialog.open(DialogDeleteBadgeComponent, {panelClass: 'delete-dialog', data: {badgeId: badge.id, badgeName: badge.name}});

    deleteBadgeDialog.afterClosed().subscribe(result => {
      if (result === 'confirm') {
        this.alertMsg = 'Badge deleted';
        this.getAllBadges(this.alertMsg);
      }
    });

  }

  openDialogEditBadge(badge: OneBadgeResponse) {
    const updateBadgeDialog = this.dialog.open(DialogBadgeComponent, {panelClass: 'add-dialog', data: {badge: badge}});

    updateBadgeDialog.afterClosed().subscribe(result => {
      if (result === 'confirm') {
        this.alertMsg = 'Badge updated';
        this.getAllBadges(this.alertMsg);
      }
    });
  }

}
