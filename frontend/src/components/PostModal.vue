<template>
    <div v-if="this.entity != null">
      <b-modal id="modalAdditionalPost" size="xl" :hide-footer="true" :title="'@' + this.entity.user.username">
        <b-card no-body class="overflow-hidden" style="max-width: auto; max-height: auto; margin-top: 0px;">
          <b-row no-gutters>
            <b-col md="8">
              <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <b-card-img :src="this.entity.imagesAndVideos[i]" alt="Image" class="rounded-0"></b-card-img>
                </div>
                <a class="carousel-control-prev" role="button" v-on:click="previus" data-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" role="button" v-on:click="next" data-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="sr-only">Next</span>
                </a>
              </div>
            </b-col>
            <b-col md="4">
		          <b-card-body>
                <b-card-text>
                  <p v-if="this.entity.location != null" style="font-size:20px"><b>{{this.entity.location.street}}, {{this.entity.location.city}}, {{this.entity.location.country}}</b></p>
                  <p>{{this.entity.caption.description}}</p>
                  <p v-for="t in this.entity.caption.tags" :key="t.id">{{t.name}}</p>
                </b-card-text>
                <b-card-text>
                    <div class="container-column" v-for="comment in entity.comments" :key="comment.id" >
                        <p><b style="font-size:14px;">@{{comment.user.username}}</b> {{comment.content}}</p>
                        <div class="container-row">
                            <p v-for="tag in comment.tags" :key="tag.id">{{tag.name}}</p>
                        </div>
                    </div>
                </b-card-text>
              </b-card-body>
            </b-col>
          </b-row>
        </b-card>
      </b-modal>
    </div>
</template>

<script>
export default {
    name: 'PostModal',
    computed: {
        entity() {
            return this.$store.getters.getEntity;
        }
    },
    data() {
        return {
            i: 0
        }
    },
    methods: {
        previus() {
            var i = this.i - 1
            if(i < 0) {
                this.i = this.entity.imagesAndVideos.length-1
                return
            }
            this.i = i
        },
        next() {
            var i = this.i + 1
            if(i >= this.entity.imagesAndVideos.length) {
                this.i = 0
                return
            }
            this.i = i
        }
    },
    mounted() {
        this.$root.$on('bv::modal::hidden', (bvEvent, modalPost) => {
            this.i = 0
        })
    }
}
</script>

<style scoped>
    .container-row {
        display: flex;
        flex-direction: row;
    }

    .container-column {
        display: flex;
        flex-direction: column;
    }
</style>