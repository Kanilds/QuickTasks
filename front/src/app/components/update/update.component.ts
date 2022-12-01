import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuickTask } from 'src/app/models/quickTask';
import { QuickTaskService } from 'src/app/services/quickTask.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  quickTask: QuickTask = {
    titulo: '',
    descricao: '',
    dataParaFinalizar: new Date(),
    finalizado: false
  }

  constructor(private router: Router, private service: QuickTaskService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.quickTask.id = this.route.snapshot.paramMap.get("id")!;
    this.findById();
  }

  findById(): void {
    this.service.findById(this.quickTask.id).subscribe((resposta) => {
      this.quickTask = resposta;
    })
  }

  update(): void {
    this.formataData();
    this.service.update(this.quickTask).subscribe((resposta) => {
      this.service.message('Informações atualizadas com sucesso!');
      this.router.navigate(['/tasks']);
    }, error => {
      this.service.message('Falha ao atualizar Task!');
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
