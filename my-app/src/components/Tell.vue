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
                <div><h3>{{titre}}</h3></div>
                <v-spacer></v-spacer>
                <div><h4>{{element}}</h4></div>

            </v-card-text>
        
        

        </v-card>

        {{message}}
    </div>
</div>
    
</template>
<script>
export default {
    name: "Tell",
    props:["titre", "element"],
    data: ()=>({
        message:""
    }),
    methods:{
        send: function(){
            console.log(this.element)
            if(this.element==""){
                this.message="Board crée"
            }else{
                this.message="Element ajouté au board: "+this.titre
            }
        }
    }
}
</script>

<style scoped>


</style>