import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  usuarioLogado!: string;

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.usuarioLogado = this.authService.getUsuarioLogado();
  }

}
