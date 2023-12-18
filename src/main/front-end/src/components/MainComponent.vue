<script setup>
    // IMPORTS

    import { onMounted, ref } from 'vue';
    import axios from 'axios';
    import IndexComponent from './IndexComponent.vue';
    import ShowComponent from './ShowComponent.vue';
    import FormComponent from './FormComponent.vue';
   
    // DATA
    const photos= ref(null)

    const selectedPhoto=ref(null)


    // Functions
    
    const getPhotos = async () => {
        const data = await axios.get("http://localhost:8080/api/photos/");
        photos.value = data.data;
    }
 
    const getSelectedPhoto = (photo)=>{
        console.log('photo', photo)
        selectedPhoto.value=photo
    }
    
    const resetSelectedPhoto=()=>{

        selectedPhoto.value=null;
        
    }

    // Hooks
    onMounted(getPhotos)
</script>

<template>
    <main>

        <div class="container">

            <IndexComponent v-if="selectedPhoto==null " :photos="photos" @that-photo="getSelectedPhoto" @create="creating=true"/>
    
            <ShowComponent v-if="selectedPhoto!=null " :selectedPhoto="selectedPhoto" @back-to-index="resetSelectedPhoto" @edit="getOldPhoto" @delete-photo="afterDelete"/>
    
            <FormComponent v-if="selectedPhoto==null" />

        </div>

    </main>
</template>

<style lang="scss" scoped>

</style>