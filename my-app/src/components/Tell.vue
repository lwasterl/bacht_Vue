<template>
<div>
    <!--TODO ajoute if quand envoye-->

           <div>   
                <v-row  align="center" justify="center">
                <v-col  class="d-flex" cols="12" sm="4">
                    <v-text-field
                   
                    label="Element (si vide, création d'un nouveau Board)"
                    v-model="element">

                    </v-text-field></v-col>
                </v-row>
                <v-row align="center" justify="center">
                    <div v-if="element===''">
                        <h3>Modifiable par:</h3>
                         <v-checkbox v-model="selected" label="Etudiant" value="eleve"></v-checkbox>
                         <v-checkbox v-model="selected" label="Professeur" value="prof"></v-checkbox>
                         <v-checkbox v-model="selected" label="Assistant" value="assistant"></v-checkbox>
                         <v-checkbox v-model="selected" label="Doyen" value="doyen"></v-checkbox>

                    </div>
                </v-row>

                    <v-row>
                        <v-col>
                            <v-btn color="primary" v-on:click="send">Envoyer</v-btn>
                        </v-col>
                </v-row>
            
            </div>


    <div>
        <v-card
        class="mx-auto"
        max-width="344"
        outlined="">
            <v-card-text>
                <div><h3>{{title}}</h3></div>
                <v-spacer></v-spacer>
                <div><h4>{{bodycard}}</h4></div>

            </v-card-text>
        
        

        </v-card>
        {{message}}
    </div>
</div>
    
</template>
<script>
export default {
    name: "Tell",
    props:["titre", "element", "user"],
    data: ()=>({
        message:"",
        selected:[],
        title:"",
        body:"",
        items: ['eleve', 'assistant', 'doyen', 'prof'],
        bodycard:""
    }),
    methods:{
        send: function(){
            
            if(this.element==""){
                console.log(this.selected.length==0)
                if(this.selected.length==0){
                    this.message="Vous devez selectionner au moins un role"
                }else{
                this.title=this.titre
                const postData = {"titleBoard":this.titre,"userId":this.user,"listRoles": this.selected};
                this.$http
                        .post("http://localhost:8080/tell/board", postData)
                        .then(res => {
                            console.log(res.body);
                            
                        });
                this.message="Le board "+this.titre+" a été crée"        }
            }else{
                this.title=this.titre
                this.bodycard=this.element
                const postData = {"titleBoard":this.titre,"userId":this.user,"elementId":this.element, "body":this.element};
                this.$http
                        .post("http://localhost:8080/tell/element", postData)
                        .then(res => {
                            console.log(res.body);
                            if(res.body.message=="User has not the permission to do that"){
                                this.message="Vous n'avez pas la permission de modifier ce Board"
                            }
                      });
                
            }
        }
    }
}
</script>

<style scoped>


</style>