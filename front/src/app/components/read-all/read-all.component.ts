import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { QuickTask } from "src/app/models/quickTask";
import { QuickTaskService } from "src/app/services/quickTask.service";

@Component({
  selector: "app-read-all",
  templateUrl: "./read-all.component.html",
  styleUrls: ["./read-all.component.css"],
})
export class ReadAllComponent implements OnInit {
  closed = 0;

  list: QuickTask[] = [];
  listFinished: QuickTask[] = [];

  constructor(private service: QuickTaskService, private router: Router) {}

  ngOnInit(): void {
    this.findAll();
  }

  load() {
    location.reload()
  }
  findAll(): void {
    this.service.findAll().subscribe((resposta) => {
      resposta.forEach((quickTask) => {
        if (quickTask.finalizado) {
          this.listFinished.push(quickTask);
        } else {
          this.list.push(quickTask);
        }
      });
      this.closed = this.listFinished.length;
    });
  }

  finalizar(item: QuickTask): void {
    item.finalizado = true;
    this.service.update(item).subscribe(() => {
      this.service.message("Task finalizada com sucesso!");
      this.list = this.list.filter((quickTask) => quickTask.id !== item.id);
      this.closed++;
    });
  }

  create(){
    this.router.navigate(['/create'])
  }

  update(item: QuickTask){
    this.router.navigate(['/update/' + item.id])
  }

  delete(id: any): void {
    this.service.delete(id).subscribe((resposta) => {
      if (resposta === null) {
        this.service.message("Task deletada com sucesso!");
        this.list = this.list.filter((quickTask) => quickTask.id !== id);
      }
    });
  }

  navegarParaFinalizados(): void {
    this.router.navigate(["finalizados"]);
  }
}
