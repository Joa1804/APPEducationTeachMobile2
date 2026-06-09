package com.example.appeducacationteach;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appeducacationteach.dao.UsuarioAlDAO;
import com.example.appeducacationteach.dao.UsuarioProfDAO;
import com.example.appeducacationteach.model.UsuarioProfessor;

public class MainLogin extends AppCompatActivity {

    private Button Entrarda;
    private EditText Senha, Email;
    private TextView Cadastro;
    private UsuarioAlDAO usuarioAlDAO;
    private UsuarioProfDAO usuarioProfDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Entrarda = findViewById(R.id.btnEntrada);
        Senha = findViewById(R.id.edtSenha);
        Email = findViewById(R.id.edtEmail);
        Cadastro = findViewById(R.id.textCadastro);

        usuarioAlDAO = new UsuarioAlDAO(this);
        usuarioProfDAO = new UsuarioProfDAO(this);

        Cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PaginadeCadastro = new Intent(MainLogin.this, MainCadastro.class);
                startActivity(PaginadeCadastro);
            }
        });


        Entrarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailDigital = Email.getText().toString().trim();
                String senhaDigital = Senha.getText().toString().trim();

                if (emailDigital.isEmpty() || senhaDigital.isEmpty()) {
                    Toast.makeText(MainLogin.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (usuarioAlDAO.login(emailDigital, senhaDigital)) {
                    navegarParaHome("aluno");

                } else if (usuarioProfDAO.login(emailDigital, senhaDigital)) {
                    navegarParaHome("professor");

                } else {
                    Toast.makeText(MainLogin.this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

        private void navegarParaHome(String tipoUsuario) {
            Intent telaPrincipal;

            if (tipoUsuario.equals("professor")) {
                telaPrincipal = new Intent(MainLogin.this, MainTeacher.class);
            } else {
                telaPrincipal = new Intent(MainLogin.this, MainActivity.class);
            }

            telaPrincipal.putExtra("tipo_usuario", tipoUsuario);
            telaPrincipal.putExtra("email_usuario", Email.getText().toString().trim());
            startActivity(telaPrincipal);
        }

}