<template>
  <b-card no-body style="flex-direction: column; max-width: 50rem; margin-top: 50px; border-radius: 10px;
    box-shadow: 12px 12px 12px 0 rgba(0,0,0,0.2);">
      <template #header>
        <b-row no-gutters>
            <b-col md="11">
                    <h4 class="mb-0">@imenko_prezimenić</h4>
            </b-col>
            <b-col md="1" style="text-align:right">
                 <b-icon icon="exclamation-triangle" scale="1.25" v-b-modal.modal-tall v-b-tooltip.hover.right.v-danger="'Report content'" variant="danger" ></b-icon>
                 <b-modal id="modal-tall" :hide-footer="true" title="REPORT CONTENT">
                    <b-form-group label="Report for:" v-slot="{ ariaDescribedby }" style="margin-left: 120px;">
                          <b-form-radio-group
                            id="btn-radios-3"
                            v-model="selected"
                            :options="options"
                            :aria-describedby="ariaDescribedby"
                            button-variant="outline-danger"
                            name="radio-btn-stacked"
                            buttons
                            stacked
                          ></b-form-radio-group>
                    </b-form-group>
                    <b-button variant="danger" style="margin-left: 200px; margine-top: 10px; margine-bottom: 10px;">Report</b-button>
                  </b-modal>
            </b-col>
          </b-row>
      </template>

      <b-card-body style="padding-top: 10px; padding-bottom: 0px;">
        <b-card-sub-title class="mb-2">Location</b-card-sub-title>
      </b-card-body>

      <b-card-img src="https://placekitten.com/480/210" alt="Image"></b-card-img>

      <b-card-body>
        <b-row no-gutters>
            <b-col md="11">
                <b-card-text>
                  This is the text area for post captions.
                </b-card-text>
            </b-col>
            <b-col md="1" style="text-align:right">
                 <b-icon icon="bookmark" scale="1.5" v-b-modal.modal-center v-b-tooltip.hover.right="'Add to favourites'"></b-icon>
                 <b-modal id="modal-center" :hide-footer="true" title="Add to collection" style="height: 500px;">
                   <b-row no-gutters>
                      <b-col sm="8">
                        <b-form-input type="text" v-model="user.email" placeholder="Collection name" style="font-style:italic; width: 300px;"/>
                      </b-col>
                      <b-col sm="4">
                        <b-button variant="primary" style="margine-top: 10px; margine-bottom: 10px;">Create collection</b-button>
                      </b-col>
                    </b-row>
                    <b-row no-gutters>
                      <b-col sm="4">
                        <b-card-text style="margin-top: 15px; font-size: 20px;">
                          Select collection:
                        </b-card-text>
                      </b-col>
                      <b-col sm="8">
                        <b-form-input list="my-list-id" style="margin: 10px;"></b-form-input>
                        <datalist id="my-list-id">
                          <option>Manual Option</option>
                          <!--<option v-for="size in sizes">{{ size }}</option>-->
                        </datalist>
                      </b-col>
                    </b-row>
                    <div style="height: 100px;" ></div>
                    <b-button variant="primary" style="margin-left: 180px; margine-top: 10px; margine-bottom: 10px;">Add post</b-button>
                  </b-modal>
            </b-col>
        </b-row>
        <b-row no-gutters>
          <b-icon icon="hand-thumbs-up" scale="1.5" v-b-tooltip.hover.left.v-success="'I like this!'" variant="success" style="margin-top: 10px; margin-bottom: 0px; margin-left: 10px; margin-right: 10px;" ></b-icon>
          <b-icon icon="hand-thumbs-down" scale="1.5" v-b-tooltip.hover.right.v-danger="'Dislike'" variant="danger" style="margin-top: 10px; margin-bottom: 0px; margin-left: 10px; margin-right: 10px;"></b-icon>
        </b-row>
      </b-card-body>

      <b-list-group flush>
        <b-list-group-item>Comments</b-list-group-item>
      </b-list-group>

      <div>
        <b-form-textarea
          id="textarea-state" 
          v-model="text1" 
          :state="text.length <= 300"
          placeholder="Leave a comment..."
          rows="3"
          no-resize
        ></b-form-textarea>
      </div>

      <b-card-footer footer-text-variant="muted">3 days ago</b-card-footer>

    </b-card>
</template>

<script>
export default {
    name: "Post",
    computed: {
        user(){
            return this.$store.state.user
        },
        isUserLogged() {
            return this.$store.state.isUserLogged
        },
        userFullname() {
            return this.$store.getters.getFullName
        }
    },
    data() {
      return {
        selected: 'radio1',
        options: [
          { text: 'UNWANTED CONTENT', value: 'radio1' },
          { text: 'NUDETY OR SEXUAL ACTIVITY', value: 'radio2' },
          { text: 'HATE SYMBOL', value: 'radio3' },
          { text: 'VIOLENCE', value: 'radio4' },
          { text: 'ILLEGAL SALE', value: 'radio5' },
          { text: 'HARASSMENT', value: 'radio6' },
          { text: 'COPYRIGHT INFRIGEMENT', value: 'radio7' },
          { text: 'SUICIDE', value: 'radio8' },
          { text: 'SCAM', value: 'radio9' },
          { text: 'FALSE_INFORMATION', value: 'radio10' }
        ],
        text1:''
      }
    },
    methods: {
      text(){

      }
    },
}
</script>
