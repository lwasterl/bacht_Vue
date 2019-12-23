<template>
<div>
    <!--TODO ajoute if quand envoye-->

           <div>   
                <v-row  align="center" justify="center">
                <v-col  class="d-flex" cols="12" sm="4">
                    <v-text-field
                   
                    label="Element (si vide, on questionne le Board)"
                    v-model="element">

                    </v-text-field></v-col>
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
    props:["titre", "element"],
    data: ()=>({
        message:"",
        boards: boards
    }),
    methods:{
        send: function(){
                for (let i = 0; i < boards.length; i++) {
                    if(boards[i]["title"]==this.titre){
                        if(this.element==''){
                            
                            this.message=boards[i]["title"] + " existe"
                            return
                        }else{
                            for (let j = 0; j < boards.length; j++) {
                                console.log(boards[i][j])
                                if(boards[i]["element"][j]==this.element){
                                    this.message="l'élement: "+boards[i]["element"][j]+ " existe" 
                                    return
                                }
                            }
                        }
                        this.message="l'élement: "+this.element+ " n'existe pas dans le Board "+ this.titre
                        return  
                        
                    }this.message=this.titre + " n'existe pas"
                    
                }
                
        }
    }
}
</script>

<style scoped>


</style>