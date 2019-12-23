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
      <Main/>
    </v-content>
    <v-content v-else id="connect">
      <v-row align="center" justify="center">
        <v-col>
       <v-btn color="primary"  v-on:click="addUser">Ajouter un utilisateur</v-btn>
        
        </v-col>

        <v-col>

          <v-btn color="primary" v-on:click="connect">Se connecter</v-btn>
        </v-col>
      </v-row>

    </v-content>

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
    id:""
  }),

  methods:{
    addUser: function(){

    },
    deco:function(){
      this.id="",
      this.connected=false
    },
    connect:function(){

      this.dialog_connect=true;

    },
    testCo:function(){
      for (let index = 0; index < users.length; index++) {
        if(this.id == this.users[index]["Nom"]){
          this.connected=true
          this.dialog_connect=false
        }
        
      }
    }



  }
};
</script>

<style scoped>
.v-content{
  text-align :center;
}

</style>
