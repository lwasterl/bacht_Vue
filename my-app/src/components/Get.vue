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
                <div><h1>{{titre}}</h1></div>
                <v-spacer></v-spacer>
            </v-card-text>
        </v-card>
        <Cards :list="coucou" />
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
    props:["titre", "element"],
    data: ()=>({
        message:"",
        all_elem:false,
        sending:"",
        boards:boards
    }),
    methods:{
        send: function(){
            console.log(this.element)
            if(this.element!=""){
                this.all_elem=false
                this.message="élément: "+this.element+" supprimé"
            }else{
                this.all_elem=true;
                for (let i = 0; i < boards.length; i++) {
                    if(boards[i]["title"]==this.titre){
                        this.sending=boards[i]
                    }
                    
                }
              this.message=this.titre+" supprimé"  
            }
            
        }
    }
}
</script>

<style scoped>


</style>