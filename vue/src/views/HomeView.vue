<template>
   <ElTabs type="card" style="height: 100%;" v-model="tabName">
      <ElTabPane label="首页" name="首页">首页</ElTabPane>
      <ElTabPane label="赛事" name="赛事">赛事</ElTabPane>
      <ElTabPane label="个人" name="个人">个人</ElTabPane>
   </ElTabs>
</template>
<script setup lang="ts">

import logo from "@/assets/logo.png"
import type { TabsPaneContext } from "element-plus";
import {ref} from "vue"

let tabName = ref("首页")

</script>

<style scoped>
:deep(.el-tabs__header) {
   background-color: #ff4d00;
}

:deep(.el-tabs__item) {
   opacity: 0.8;
   background-color: aliceblue;
   width: 100px;
   font-size: large;
}
</style>