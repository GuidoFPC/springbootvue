<template>
  <div id="app" class="container">
    
    <Message :msg="msg" v-show="msg" ></Message>
    <router-view></router-view>
  </div>
</template>

<script>
import Message from './components/Message.vue'

export default {
  data() {
    return {
      msg: ""
    };
  },
  components:{
    Message
  }
}
</script>


