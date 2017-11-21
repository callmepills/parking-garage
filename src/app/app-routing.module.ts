import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {GarageComponent} from './garage/garage.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'garages/:name', component: GarageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
