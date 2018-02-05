import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {Question} from './question';
import {NgForm} from '@angular/forms';

const QUESTIONS: Array<Question> = [
  new Question('Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 4),
  new Question('Aliquam egestas, est eu viverra fringilla, erat enim euismod orci, non imperdiet sapien turpis tincidunt quam.', 1),
  new Question('Vestibulum consectetur orci id massa suscipit fringilla.', 3),
  new Question('Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In maximus leo ' +
    'elit, nec scelerisque nisl laoreet ultrices.', 2)
];

@Component({
  selector: 'app-garage',
  templateUrl: './garage.component.html',
  styleUrls: ['./garage.component.scss']
})
export class GarageComponent implements OnInit {
  name: string;
  questions: Array<Question>;

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.name = this.route.snapshot.paramMap.get('name');
    this.questions = QUESTIONS;
  }

  addQuestion(form: NgForm) {
    this.questions.push(new Question(form.value.question, 0));
    form.reset();
  }

  likeQuestion(question: Question) {
    question.likes++;
  }
}
