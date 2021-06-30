<template>
    <div class="container-story1">
        <div class="container-story2" v-for="story in stories" :key="story.id">
            <div class="container-1" v-b-modal.modalStory v-on:click="changeStory(story)"> 
            <b-img-lazy class="item-story2" rounded="circle" :src="story.imagesAndVideos[0]" alt="Image"></b-img-lazy>
            <label style="text-align:center;">{{story.user.username}}</label>
            </div>
        </div>
        <div v-if="this.story != null">
            <b-modal id="modalStory" size="lg" :hide-header="true" :hide-footer="true">
                <b-card no-body class="overflow-hidden" style="max-width: auto; max-height: auto; margin-top: 0px;">
                <b-row no-gutters>
                    <b-col md="12" style="text-align:center">
                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <b-card-img :src="this.story.imagesAndVideos[i]" alt="Image" class="rounded-0"></b-card-img>
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
                    <!-- <b-icon v-if="isUserLogged" icon="bookmark" style="margin:10px" scale="1.5" v-b-modal.modalFavorites v-b-tooltip.hover.right="'Add to highlight'" v-on:click="setType(story)"></b-icon>   -->
                    </b-col>
                </b-row>
                </b-card>
            </b-modal>
        </div>
    </div>
</template>

<script>

import axios from 'axios'

export default {
    name: 'Story',
    computed: {
        User() {
            return this.$store.getters.getUser
        },
        isUserLogged() {
            return this.$store.getters.isUserLogged
        }
    },
    data() {
        return {
            stories: [],
            story: null,
            i: 0
        }
    },
    methods: {
        changeStory(story) {
            this.story = story;
        },
        previus() {
            var i = this.i - 1
            if(i < 0) {
                this.i = this.story.imagesAndVideos.length-1
                return
            }
            this.i = i
        },
        next() {
            var i = this.i + 1
            if(i >= this.story.imagesAndVideos.length) {
                this.i = 0
                return
            }
            this.i = i
        },
        setType(story) {
          var type = 'highlight'
          this.$store.dispatch('updateType', type)
          this.$store.dispatch('updateEntity', story)
        }
    },
    mounted() {
        var id;
        if(this.User.id == null) id = -1;
        else id = this.User.id; 
        axios.get(this.$store.getters.getStoryAPI + "/api/story/get-story-for-feed/" + id)
            .then(r => {
            var stories = JSON.parse(JSON.stringify(r.data));
            stories.forEach(post => {
            axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + post.user.id)
                .then(r => {
                    post.user = JSON.parse(JSON.stringify(r.data))
                })
            })
            this.stories = stories
        })
        this.$root.$on('bv::modal::hidden', (bvEvent, modalStory) => {
            this.i = 0
        })
    }
}
</script>

<style scoped>

  .container-1 {
    display: flex;
    flex-direction: column;
  }

  .container-story1 {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    background-color: whitesmoke;
    border-radius: 20px;
    box-shadow: 12px 12px 12px 0 rgba(0,0,0,0.2);
    margin:10px;
    /* justify-content:space-between; */
  }
  .container-story2 {
    transition: 0.2s all ease-in-out;
  }

  .container-story2:hover {
    margin-top: -10px;
  }

  .item-story2 {
    width: 84px;
    height: 84px;
    margin: 10px;
  }

</style>