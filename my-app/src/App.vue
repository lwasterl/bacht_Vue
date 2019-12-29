<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
       <h1>BachT Padlet</h1>
      </div>

      

      <v-spacer></v-spacer>
        <h4 v-if="connected">Connecté en tant que : {{id}}</h4>
        <v-spacer></v-spacer>
        <v-btn v-if="connected" v-on:click="deco">Se déconnecter</v-btn>
    </v-app-bar>

    <v-content v-if="connected">
      <Main :user="id"/>
    </v-content>
    <v-content v-else id="connect">
      <v-row align="center" justify="center">
        <v-col>
       <v-btn color="primary"  v-on:click="ajout">Ajouter un utilisateur</v-btn>
        
        </v-col>

        <v-col>

          <v-btn color="primary" v-on:click="connect">Se connecter</v-btn>
        </v-col>
      </v-row>


    </v-content>


<v-dialog
  v-model="dialog_add"
  max-width="290"
  >
    <v-card>
       <v-card>
           <v-card-title class="headline">Ajouter un utilisateur</v-card-title>
           
              <v-text-field label="Nom" v-model="add_name"></v-text-field>
                         <v-spacer></v-spacer>
              <v-text-field label="Role" v-model="add_role"></v-text-field>

           
                   
          <v-card-actions>
                       <v-spacer></v-spacer>
  
  
            <v-btn
              color="green darken-1"
              text
              v-on:click=addUser
            >
            Ajouter
            </v-btn>
          </v-card-actions>
        </v-card>
    </v-card>

</v-dialog>

<v-dialog
        v-model="dialog_connect"
        max-width="290"
      >
        <v-card>
           <v-card-title class="headline">Connexion</v-card-title>
                    <v-text-field
                    label="Nom"
                    v-model="id"
                    ></v-text-field>
          <v-card-actions>
            <v-spacer></v-spacer>
  
  
            <v-btn
              color="green darken-1"
              text
              v-on:click=testCo
            >
            Se connecter
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
  </v-app>
</template>

<script>
import Main from './components/Main';
import users from './assets/users.json';

export default {
  name: 'App',

  components: {
    Main
  },

  data: () => ({
    connected:false,
    users,
    dialog_connect:false,
    dialog_add:false,
    add_name:"",
    add_role:"",
    id:"",
    post:"",
    title:"",
    body:"",
  }),

  created(){
        this.$http.get("http://localhost:8080/readall/board")
    .then(res => {
      this.post = res.body;});

  },
  methods:{
    addUser: function(){{
      const postData = { "name": this.add_name, "role": this.add_role};
      this.$http
        .post("http://localhost:8080/tell/user", postData)
        .then(res => {
          console.log(res.body);
        });
        this.dialog_add=false;
    }
  },
    deco:function(){
      this.id="",
      this.connected=false
    },
    connect:function(){

      this.dialog_connect=true;

    },

    ajout:function(){
      this.dialog_add=true;
    },
    testCo:function(){

      const postData = { "name": this.id};
      this.$http
        .post("http://localhost:8080/read/user", postData)
        .then(res => {
          console.log(res.body);
        });
          this.connected=true
          this.dialog_connect=false
        
    }



  }
};
</script>

<style scoped>
.v-content{
  text-align :center;
}

</style>
