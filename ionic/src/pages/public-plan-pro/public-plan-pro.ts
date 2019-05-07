import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ViewChild } from '@angular/core';
import { Slides } from 'ionic-angular';
import { PlansProvider } from "../../providers/plans/plans";
/**
 * Generated class for the PublicPlanProPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-public-plan-pro',
  templateUrl: 'public-plan-pro.html',
})
export class PublicPlanProPage {
  @ViewChild(Slides) slides: Slides;

  private plans:any;
  constructor(public navCtrl: NavController, public navParams: NavParams,plansProvider:PlansProvider) {
    plansProvider.isDeveloper("sfsdf");
    var typePlan={
      "idOperator": "ESMERO",
      "idTerritorialDivision": 38,
      "idTerritorialDivisionLevel": 1,
      "idTypeSubscriber": 2,
      "idTypePlan": "I",
      "requestDate": "2019-05-06T00:00:00"
    }
   
    plansProvider.getTariffPlanList("",typePlan).then(data => {
      this.plans=data;
    }) ;
   
   
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad PublicPlanProPage');
  }
  goToSlide() {
    this.slides.slideTo(2, 500);
  }
}
