<template>
    <div>
        <b-modal id="modalVerifyProfile" :hide-header="true" :hide-footer="true">
            <div class="container-row">
                <div class="container-column">
                    <h1 class = "title" style="color:#4d4d4d ">Verify profile</h1>
                    <input style="margin-top:10px;" type="file" @change="onFileSelected" multiple>
                    <img style="margin:10px" class="image" :src="url" />
                    <b-form-group label="Category type:" v-slot="{ ariaDescribedby }">
                        <b-form-radio-group
                            id="btn-radios-3"
                            v-model="selectedCategory"
                            :options="options"
                            :aria-describedby="ariaDescribedby"
                            button-variant="outline-danger"
                            name="radio-btn-stacked"
                            buttons
                            stacked
                        ></b-form-radio-group>
                    </b-form-group>
                    <b-button style="color: white" v-on:click="upload" >{{status}}</b-button>
                </div>
            </div>
        </b-modal>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "VerifyProfile",
  computed: {
      User() {
          return this.$store.getters.getUser
      }
  },
  data() {
    return {
        url: '',
        selected: null,
        selectedCategory: '',
        options: [],
        status: 'Verify'
    }
  },
  methods: {
      onFileSelected(event) {
            this.url = ''
            this.selected = event.target.files[0]
            this.url = (URL.createObjectURL(this.selected));
        },
        async upload() {
            
            if(this.status == 'Request sended') {
                alert("Verification is in progress.")
                return
            }

            if(this.selectedCategory == '') {
                alert("Select category!")
                return
            }

            var date = (new Date()).getTime();
            var image = ''
            const fileToUpload = new FormData();
            image = this.User.username + '-verificaion-' + date + ".jpg"
            fileToUpload.append('file', this.selected, image)
            image = "https://nistagramstorage.s3.eu-central-1.amazonaws.com/" + image
            axios.post(this.$store.getters.getStoryAPI + "/api/upload/upload-file", fileToUpload);

            var request = {
                userId: this.User.id,
                image: image,
                category: this.selectedCategory
            }

            await axios.post(this.$store.getters.getAdminAPI + "/api/verificationrequest/request-verification", request)
                .then(r => {
                    alert("Verification is sended!");
                })

            this.status = 'Request sended'

            this.$store.dispatch('updateVerifyStatus', 'Verification is in progress')

        }
    },
    mounted() {
        axios.get(this.$store.getters.getAdminAPI + "/api/categorytype/get-category-types")
            .then(r => {
                this.options = JSON.parse(JSON.stringify(r.data))
            })
        this.$root.$on('bv::modal::hidden', (bvEvent, mmodalVerifyProfile) => {
            this.url = []
            this.selected = null;
            this.selectedCategory = ''
        })
    }
};
</script>

<style scoped>

.container-row {
    display: flex;
    flex-flow: row;
    justify-content: center;
}

.container-column {
    display: flex;
    flex-flow: column;
    align-items: center;
}

.title {
  font-family:sans-serif;
  font-size: 30px;
  color: black;
}

.app {
  padding: 20px;
}

.image{
    max-width: 420px;
    max-height: 500px;
}

</style>