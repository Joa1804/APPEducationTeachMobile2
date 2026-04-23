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

public class MainLogin extends AppCompatActivity {

    private Button Entrarda;
    private EditText Senha, Email;
    private TextView Cadastro;
    private UsuarioAlDAO usuarioAlDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Entrarda = findViewById(R.id.btnEntrada);
        Senha = findViewById(R.id.edtSenha);
        Email = findViewById(R.id.edtEmail);
        Cadastro = findViewById(R.id.textCadastro);

        usuarioAlDAO = new UsuarioAlDAO();

        String textcadastra = "Cadastra";
        SpannableString ss = new SpannableString(textcadastra);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                Intent PaginadeCadastro = new Intent(MainLogin.this, MainCadastro.class);
                startActivity(PaginadeCadastro);

            }
        };

        ss.setSpan(clickableSpan, 0, textcadastra.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        Cadastro.setText(ss);
        Cadastro.setMovementMethod(LinkMovementMethod.getInstance());


        Entrarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailDigital = Email.getText().toString();
                String senhaDigital = Senha.getText().toString();

                if (usuarioAlDAO.login(emailDigital, senhaDigital)) {
                    navegarParaHome();
                } else {
                    Toast.makeText(MainLogin.this, "Login Invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        private void navegarParaHome() {
            Intent telaPrincipal = new Intent(MainLogin.this, MainActivity.class);
            startActivity(telaPrincipal);
        }

}