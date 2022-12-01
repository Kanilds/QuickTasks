import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { QuickTask } from 'src/app/models/quickTask';
import { QuickTaskService } from 'src/app/services/quickTask.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  quickTask: QuickTask = {
    titulo: '',
    descricao: '',
    dataParaFinalizar: new Date(),
    finalizado: false
  }

  constructor(private router: Router, private service: QuickTaskService) { }

  ngOnInit(): void {
  }

  create(): void {
    this.formataData();
    this.service.create(this.quickTask).subscribe((resposta) => {
      this.service.message('Quick Task criada com sucesso!');
      this.router.navigate(['/tasks']);
    }, err => {
      this.service.message('Falha ao criar Quick Task');
      this.router.navigate(['/tasks']);
    })
  }

  cancel(): void {
    this.router.navigate([''])
  }

  formataData(): void {
    let data = new Date(this.quickTask.dataParaFinalizar)
    this.quickTask.dataParaFinalizar = `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`
  }

}
