<template>
  <h1 style="width: 100%;height: 50px;background-color: #ff4d00;margin-top: 0;text-align: center;">{{ compName }}</h1>
  <div style="display: flex;justify-content: center; width: 100%;height:auto">
  <CompTree :tree-data="treeData" :against-data="againstData" :user-id="1" role="裁判员"></CompTree>
  </div>
</template>

<script setup lang="ts">
import defaultAvatar from '/defaultAvatar.png';
import CompTree from "@/components/CompTree.vue";

const compName = "乒乓球赛事系统";

let treeData: ({ order: number; avatar?: string; name?: string; })[] = [];
let againstData: ({ state: string; refereeId: number; record?: string; })[] = [];
for (let i = 1; i < 18; i++) {
  if (i < 5) {
    treeData.push({
      order: i,
    });
  }
  else {
    treeData.push({
      order: i,
      avatar: defaultAvatar,
      name: "No." + i
    });
  }
}
for(let i=1;i<9;i++){
  let state="未开始";
  if(treeData[2*i-1].name&&treeData[2*i]){
    state = "进行中";
  }
  if(treeData[i-1].name){
    state = "已结束";
  }
  if(state=="已结束"){
    if(i<8){
      againstData.push({
        state:state,
        refereeId:1
      });
    }
    else{
      againstData.push({
        state:state,
        refereeId:1,
        record:state
      });
    }
  }
  else{
    againstData.push({
      state:state,
      refereeId:1
    })
  }
}


</script>

<style scoped>

</style>
