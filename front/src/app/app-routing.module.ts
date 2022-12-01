import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateComponent } from './components/create/create.component';
import { FinalizadosComponent } from './components/finalizados/finalizados.component';
import { ReadAllComponent } from './components/read-all/read-all.component';
import { UpdateComponent } from './components/update/update.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.guard';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '', component: LayoutComponent, children: [
      {
        path: 'tasks',
        component: ReadAllComponent, canActivate: [AuthGuard]
      },
      {
        path: 'finalizados',
        component: FinalizadosComponent, canActivate: [AuthGuard]
      },
      {
        path: 'create',
        component: CreateComponent, canActivate: [AuthGuard]
      },
      {
        path: 'tasks/update/:id',
        component: UpdateComponent, canActivate: [AuthGuard]
      },
      {
        path: '',
        redirectTo: '/tasks', pathMatch: 'full'
      }
    ]
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
