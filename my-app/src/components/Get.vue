<template>
<div>
    <!--TODO ajoute if quand envoye-->

           <div>   
                <v-row  align="center" justify="center">
                <v-col  class="d-flex" cols="12" sm="4">
                    <v-text-field
                   
                    label="Element (si vide, suppression du Board)"
                    v-model="element">

                    </v-text-field></v-col>
                </v-row>

                <v-row  align="center" justify="center">
                <v-col  class="d-flex" cols="12" sm="4">
                    <v-text-field
                   
                    label="Auteur du board"
                    v-model="auteur">

                    </v-text-field></v-col>
                </v-row>

                    <v-row>
                        <v-col>
                            <v-btn color="primary" v-on:click="send">Envoyer</v-btn>
                        </v-col>
                </v-row>
            
            </div>


    <div v-if="message!==''">
        <v-card
        class="mx-auto"
        max-width="344"
        outlined="">
            <v-card-text>
                <div><h1>{{board}}</h1></div>
                <v-spacer></v-spacer>
            </v-card-text>
        </v-card>
        <div v-if="all_elem">
            <Cards :list="sending" />
        </div>
        <div v-else>
            <v-card
            class="mx-auto"
            max-width="344"
            outlined="">
                <v-card-text>
                    <div><h3>{{element}}</h3></div>
                    <v-spacer></v-spacer>
                </v-card-text>
            </v-card>
        </div>
        {{message}}
    </div>
</div>
    
</template>
<script>
import Cards from './Cards'
import boards from './boards.json'
export default {
    name: "Get",
    components:{
        Cards
    },
    props:["titre", "element","user"],
    data: ()=>({
        message:"",
        all_elem:false,
        sending:[],
        board:"",
        boards:boards,
        author:"",
        auteur:"",
        get:[],
        post:""
    }),
    methods:{
        send: function(){
            
            this.board=this.titre
            if(this.element!=""){
                var url = "http://localhost:8080/read/board/"+this.titre+this.auteur

                this.$http.get(url)
                        .then(res => {
                                this.post = res.body[0];
                                this.all_elem=false
                                var id="";
                                for (let i = 0; i < res.body.length; i++) {
                                        if(res.body[i].body==this.element){
                                            id=res.body[i].id
                                        }
                                    
                                }
    
                                const postDatad = {"titleBoard":this.titre,"userId":this.auteur,"elementId":id, "body":this.element}; 
                                this.$http
                                    .post("http://localhost:8080/get/element", postDatad)
                                    .then(res => {
                                    console.log(res.body)
                            
                                });
                    this.message="élément: "+this.element+" supprimé"
                 });
                
            }else{
                this.all_elem=true;
                const postData = {"titleBoard":this.titre,"userId":this.user};
                this.message=this.titre+" supprimé"  
                this.$http
                        .post("http://localhost:8080/get/board", postData)
                        .then(res => {
                            if(res.body.message=="No such board left"){
                                this.message="Ce board n'existe pas"
                            }else{
                                var tmp=res.body.elements
                                var author=res.body.owner
      
                                var index=Object.keys(tmp).length
        
                                for (let i = 0; i < index; i++) {
                                
                                    var key=this.board+author+"element"+i.toString()
        
                                    this.sending.push(tmp[key].body)
     
                                    
                    
                                }
                            }
                            
                        });
 
              
            }
            
        }
    }
}
</script>

<style scoped>


</style>