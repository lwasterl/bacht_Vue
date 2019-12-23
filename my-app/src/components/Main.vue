<template>
    <v-form>
        <v-container>

            <v-row align="center" justify="center">
                <v-col class="d-flex" cols="12" sm="4">
                    <v-select
                    :items="items"
                    filled
                    label="Action Disponible"
                    v-model="item_selected"
                    ></v-select>
                </v-col>

                <v-col cols="12" sm="4">
                    <v-text-field
                    label="Action"
                    v-model="texte"
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm="2">    
                    <v-btn color="primary" v-on:click="send">Envoyer</v-btn>
                </v-col>

            </v-row>
            <v-row align="center" justify="center">
                <v-col>BITE</v-col>
            </v-row>
             <v-row align="center" justify="center">
                 <v-col v-for="item in tmp_object" :key=item cols="12" sm="10">  
                    <Cards :value=item[0] :nb=item[1]></Cards>
                 </v-col>
             </v-row>
        </v-container>

<v-dialog
        v-model="dialog_select"
        max-width="290"
      >
        <v-card>
          <v-card-title class="headline">Choisissez une action</v-card-title>
  
  
          <v-card-actions>
            <v-spacer></v-spacer>
  
  
            <v-btn
              color="green darken-1"
              text
              @click="dialog_select = false"
            >
            D'accord
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-form>

    
</template>


<script>
import Cards from './Cards';
export default {
     name: 'Main',
     components:{
         Cards
     },
     data: () =>({
         texte:"",
         tmp_object:[],
         items: ['Ajouter un board', 'Supprimer un board', 'Voir un Board'],
         item_selected:"",
         dialog_select: false
     }),
     methods:{
         send: function(){

             switch (this.item_selected){
                 case "Tell":

                      if(this.tmp_object.length>0){
                            for (let i = 0; i < this.tmp_object.length; i++) {
                                if(this.texte==this.tmp_object[i][0]){
                                this.tmp_object[i][1]+=1;
                                return;
                            }
                            }
                            this.tmp_object.push([this.texte,1])
                        }else{
                            this.tmp_object.push([this.texte, 1])
                        }
                    break;
                case  "Get":
                    break;
                case "Ask":
                    break;
                case "Nask":
                    break;
                case "":
                    this.dialog_select=true;
                    break;

             }

               
            
             
        }
    }
}

</script>

<style scoped>

</style>