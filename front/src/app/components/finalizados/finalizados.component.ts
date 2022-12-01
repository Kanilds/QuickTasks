import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { QuickTask } from 'src/app/models/quickTask';
import { QuickTaskService } from 'src/app/services/quickTask.service';

@Component({
  selector: 'app-finalizados',
  templateUrl: './finalizados.component.html',
  styleUrls: ['./finalizados.component.css']
})
export class FinalizadosComponent implements OnInit {

  listFinished: QuickTask[] = [];

  constructor(private service: QuickTaskService, private router: Router) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.service.findAll().subscribe((resposta) => {
      resposta.forEach((quickTask) => {
        if (quickTask.finalizado) {
          this.listFinished.push(quickTask);
        } 
      });
    });
  }

  voltar(): void {
    this.router.navigate([''])
  }

}
