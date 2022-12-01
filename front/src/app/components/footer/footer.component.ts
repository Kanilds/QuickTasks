import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  usuarioLogado!: string;
  temUsuarioLogado!: boolean;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.usuarioLogado = this.authService.getUsuarioLogado();
    this.logado()
  }

  logado(){
    const logado = this.authService.getUsuarioLogado();
    if (logado){
      this.temUsuarioLogado = true
    } else {
      this.temUsuarioLogado = false
    }
    
  }

  logout(){
    this.authService.encerrarSessao();
    this.router.navigate(['/login'])
  }
}
