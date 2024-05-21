package com.example.projeto_final.projeto_final_gestao;


public class Cliente{
        private int idCliente;
        private String nomeCliente;
        private String email;
        private String numTelefone;

        public Cliente(int idCliente, String nomeCliente, String email, String numTelefone){
            this.idCliente = idCliente;
            this.nomeCliente = nomeCliente;
            this.email = email;
            this.numTelefone = numTelefone;
        }

        public Cliente(String nomeCliente, String email, String numTelefone) {
            this.nomeCliente = nomeCliente;
            this.email = email;
            this.numTelefone = numTelefone;
        }

        //Metodos getter
        public int getIdCliente(){
            return idCliente;
        }
        public String getNomeCliente(){
            return nomeCliente;
        }
        public String getEmail(){
            return email;
        }

        public String getNumTelefone(){
            return numTelefone;
        }


        //Metodo setter
        public void setIdCliente(int idCliente){
            this.idCliente = idCliente;
        }
        public void setNomeCliente(String nomeCliente){
            this.nomeCliente = nomeCliente;
        }
        public void setEmail(String email) {
            this.email = email;
        }

        public void setNumTelefone(String numTelefone) {
            this.numTelefone = numTelefone;
        }
}


