package com.example.appeducacationteach;

import android.os.Bundle;
import android.service.controls.actions.BooleanAction;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appeducacationteach.dao.UsuarioAlDAO;
import com.example.appeducacationteach.model.UsuarioAluno;

public class MainCadastro extends AppCompatActivity {

    private EditText senhaCadastro,emailCadastra, nomeCadsatro, nickCadastro;
    private Button BotaoCadastra;
    private UsuarioAlDAO usuarioAlDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);

        usuarioAlDAO = new UsuarioAlDAO(this);

        senhaCadastro = findViewById(R.id.edtSenhaCadastro);
        emailCadastra = findViewById(R.id.edtEmailCadastro);
        nomeCadsatro = findViewById(R.id.edtnomeCadastra);
        nickCadastro = findViewById(R.id.edtNicknameCadastra);
        BotaoCadastra = findViewById(R.id.btnCadastrar);

        BotaoCadastra.setOnClickListener(v -> {

            String senha = senhaCadastro.getText().toString();
            String email = emailCadastra.getText().toString();
            String nome = nomeCadsatro.getText().toString();
            String nickname = nickCadastro.getText().toString();


            if (!email.isEmpty() && !senha.isEmpty() && !nome.isEmpty() && !nickname.isEmpty())  {

                UsuarioAluno usuarioAluno = new UsuarioAluno(nome,nickname,email,senha);


                boolean sucesso = usuarioAlDAO.inserir(usuarioAluno);

                if (sucesso) {
                    Toast.makeText(this,"Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(this, "Error: usuario já existe!", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }

        });
    }
}