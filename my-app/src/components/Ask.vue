<template>
<div>
    <!--TODO ajoute if quand envoye-->

           <div>   
                <v-row  align="center" justify="center">
                <v-col  class="d-flex" cols="12" sm="4">
                    <v-text-field
                   
                    label="Element (si vide, on questionne le Board)"
                    v-model="element">

                    </v-text-field>
                    </v-col>
                    
                </v-row>
                <v-row  align="center" justify="center">
                                    <v-col  class="d-flex" cols="12" sm="4">
                    <v-text-field
                   
                    label="Auteur du board"
                    v-model="author">

                    </v-text-field>
                    </v-col>
                    
                </v-row>


                    <v-row>
                        <v-col>
                            <v-btn color="primary" v-on:click="send">Envoyer</v-btn>
                        </v-col>
                </v-row>
            {{message}}
            </div>


    <div>
    </div>
</div>
    
</template>
<script>
import boards from './boards.json';
export default {
    name: "Ask",
    props:["titre", "element", "user"],
    data: ()=>({
        message:"",
        boards: boards,
        author:""
    }),
    methods:{
        send(){
            if(this.element==""){
                const postData = {"titleBoard":this.titre,"userId":this.author};
                this.$http
                        .post("http://localhost:8080/ask/board", postData)
                        .then(res => {
                            if(res.body){
                                this.message="le board: "+this.titre+" de "+ this.author+" existe"
                            }
                            else{
                                this.message="ce board n'existe pas"
                            }
                            
                        });

            }else{
                var url = "http://localhost:8080/read/board/"+this.titre+this.author
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
                               const postData = {"titleBoard":this.titre,"userId":this.author,"elementId":id,"body": this.element};
                               this.$http
                                .post("http://localhost:8080/ask/element", postData)
                                .then(res => {
                                    if(res.body){
                                        this.message="l'element: "+this.element+" du board: "+ this.titre+ "de " +this.author +" existe"
                                    }
                                    else{
                                        this.message="cet élément n'existe pas"
                                    }
                                });
                 });
                
 

            }
        }
    }
}
</script>

<style scoped>


</style>