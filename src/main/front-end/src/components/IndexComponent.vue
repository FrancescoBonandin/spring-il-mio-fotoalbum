<script setup>
  import { defineProps, defineEmits,ref} from 'vue';  

    const word = ref("")

    const filteredResults=ref([])

    //EMIT
    const emit = defineEmits(["thatPhoto", "create"])

    // PROPS
    const props = defineProps({
        photos: {
            type: Array,
            required: true,
            default:null
        }
    });

    //FUNCTIONS 
    const search = ()=>{
        filteredResults.value=[];
        
        props.photos.forEach((photo)=>{
        console.log(word.value)
        if(word.value  != null || (typeof word.value === "string" && word.value.trim().length > 0)){

            if(photo.title.includes(word.value)){
                
                if(!filteredResults.value.includes(photo)){

                    filteredResults.value.push(photo)
                }
            }
        }

        else{
            filteredResults.value=[];
        }
        
       })
    }

</script>

<template>

    <section id="index" class="row">
    
        <h2 class="text-center my-4">
            Index
        </h2>

         <form @submit.prevent="">
            <input @keyup="search()" type="text" name="search" id="search" placeholder="search title" v-model="word">
        </form>

        <div v-if="word.trim().length == 0" class="col" >

            <div class="row justify-content-start ">



                <div class="col-4 g-4" v-for="photo in photos" :key="photo.id">

                    <div class="card" @click="$emit('thatPhoto', photo)" >

                        <img class="img-fluid card-img-top p-3" :src="photo.url" alt="">

                        <div class="card-body">

                            <p class="col">
                                {{ photo.title }}
                            </p>

                            <p class="col">
                                {{ photo.description }}
                            </p>

                            <p v-for="category in photo.categories" :key="category.id" class="col">
                                {{ category.name }}
                            </p>

                        </div>
                        
                    </div>

                </div>
                
            </div>
        </div>

        <div v-else class="col" >

            <div class="row justify-content-start ">

                <div class="col-4 g-4" v-for="photo in filteredResults" :key="photo.id">

                    <div class="card" @click="$emit('thatPhoto', photo)" >

                        <img class="img-fluid card-img-top p-3" :src="photo.url" alt="">

                        <div class="card-body">

                            <p class="col">
                                {{ photo.title }}
                            </p>

                            <p class="col">
                                {{ photo.description }}
                            </p>

                            <p v-for="category in photo.categories" :key="category.id" class="col">
                                {{ category.name }}
                            </p>

                        </div>
                        
                    </div>

                </div>
                
            </div>
        </div>
    </section>

</template>

<style lang="scss" scoped>
.card{
    cursor: pointer;
}
</style>