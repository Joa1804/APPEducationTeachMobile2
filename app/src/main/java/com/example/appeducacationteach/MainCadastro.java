package com.example.appeducacationteach;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appeducacationteach.dao.UsuarioAlDAO;
import com.example.appeducacationteach.dao.UsuarioProfDAO;
import com.example.appeducacationteach.model.UsuarioAluno;
import com.example.appeducacationteach.model.UsuarioProfessor;

public class MainCadastro extends AppCompatActivity {

    private EditText senhaCadastro, emailCadastra, nomeCadsatro, nickCadastro;
    private Button btnCadastrarAluno, btnCadastrarProfessor;
    private UsuarioAlDAO usuarioAlDAO;
    private UsuarioProfDAO usuarioProfDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);

        usuarioAlDAO = new UsuarioAlDAO(this);
        usuarioProfDAO = new UsuarioProfDAO(this);

        senhaCadastro = findViewById(R.id.edtSenhaCadastro);
        emailCadastra = findViewById(R.id.edtEmailCadastro);
        nomeCadsatro  = findViewById(R.id.edtnomeCadastra);
        nickCadastro  = findViewById(R.id.edtNicknameCadastra);
        btnCadastrarAluno     = findViewById(R.id.btnCadastrarAluno);
        btnCadastrarProfessor = findViewById(R.id.btnCadastrarProfessor);

        btnCadastrarAluno.setOnClickListener(v -> cadastrar("aluno"));
        btnCadastrarProfessor.setOnClickListener(v -> cadastrar("professor"));
    }

    private void cadastrar(String tipo) {
        String senha    = senhaCadastro.getText().toString();
        String email    = emailCadastra.getText().toString();
        String nome     = nomeCadsatro.getText().toString();
        String nickname = nickCadastro.getText().toString();
        String cpf      = "";

        if (!email.isEmpty() && !senha.isEmpty() && !nome.isEmpty() && !nickname.isEmpty()) {

            boolean sucesso;

            if (tipo.equals("professor")) {
                UsuarioProfessor professor = new UsuarioProfessor(nome, nickname, email, senha, cpf);
                sucesso = usuarioProfDAO.inserir(professor);
            } else {
                UsuarioAluno aluno = new UsuarioAluno(nome, nickname, email, senha);
                sucesso = usuarioAlDAO.inserir(aluno);
            }

            if (sucesso) {
                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro: usuário já existe!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }
}