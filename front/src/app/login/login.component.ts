import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Usuario } from './Usuario';
import { MessageService } from 'primeng/api'



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username!: string;
  password!: string;
  cadastrando!: boolean;
  mensagem!: string;
  errors!: String[];
  captchaResponse!: boolean;

  constructor(
    private router: Router,
    private authService: AuthService,
    private messageService: MessageService
  ) { }


  onSubmit() {
    if (this.captchaResponse) {
      this.authService.tentarLogar(this.username, this.password)
        .subscribe(response => {
          const access_token = JSON.stringify(response);
          localStorage.setItem('access_token', access_token)
          this.router.navigate(['/tasks'])
        }, error => {
          error
        })
    } else {
      this.errors = ["Captcha obrigatório"]
    }
  }

  showResponse(event: Event) {
    this.messageService.add({ severity: 'info', summary: 'Succees', detail: 'User Responded', sticky: true });
    if (event) {
      this.captchaResponse = true;
    } else {
      this.captchaResponse = false;
    }
  }

  preparaCadastrar(event: { preventDefault: () => void; }) {
    event.preventDefault();
    this.cadastrando = true;
  }

  cancelaCadastro() {
    this.cadastrando = false;
  }

  cadastrar(event: Event) {
    const usuario: Usuario = new Usuario();
    usuario.username = this.username;
    usuario.password = this.password;
    this.authService.salvar(usuario).subscribe(response => {
      this.mensagem = "Cadastro Realizado com Sucesso!"
      this.cadastrando = false;
      this.username = '';
      this.password = '';
      this.errors = []
      location.reload();
    }, errorResponse => {
      this.mensagem = '';
      this.errors = errorResponse.error.errors
    })
  }

}
