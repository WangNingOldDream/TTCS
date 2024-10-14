<template>
   <ElTabs type="card" v-model="tabName">
      <ElTabPane label="首页" name="首页">
         <ElRow justify="space-evenly">
            <ElCarousel height="320px" style="width: 640px;">
               <ElCarouselItem v-for="item in imgList" :key="item.name">
                  <img :src="item.img" style="height:100%;width: 100%;object-fit: contain;" />
               </ElCarouselItem>
            </ElCarousel>
            <ElTable :data="news" height="320px" style="width: 640px;background-color: aqua;">
               <ElTableColumn prop="title" label="新闻"></ElTableColumn>
               <ElTableColumn prop="date" label="发布日期" width="100px"></ElTableColumn>
            </ElTable>
         </ElRow>
         <ElRow justify="space-evenly">
            <ElTable :data="firstPageComp" height="320px" style="width: 640px;background-color: aqua;">
               <ElTableColumn prop="name" label="赛事"></ElTableColumn>
               <ElTableColumn prop="date" label="举办日期" width="100px"></ElTableColumn>
            </ElTable>
            <ElTable :data="notice" height="320px" style="width: 640px;background-color: aqua;">
               <ElTableColumn prop="title" label="公告"></ElTableColumn>
               <ElTableColumn prop="date" label="发布日期" width="100px"></ElTableColumn>
            </ElTable>
         </ElRow>
      </ElTabPane>
      <ElTabPane label="赛事" name="赛事">
         <ElRow justify="center">
            <ElInput v-model="searchText" style="width: 400px;margin-bottom: 20px;">
               <template #suffix>
                  <ElButton type="primary">搜索</ElButton>
               </template>
            </ElInput>
         </ElRow>
         <ul >
            <li v-for="i in compLineNum" :key="i" style="display: flex;justify-content: space-evenly;">
               <ElCard v-for="j in i==compLineNum?allComp.length-3*(i-1):3" :key="j" shadow="hover">
                  <img :src="allComp[3*(i-1)+j-1].cover" style="width: 100%;height: 100%;object-fit: fill;" />
                  <template #footer>
                     <ElRow justify="center">
                        <ElText style="width: 200px;" truncated size="large">
                           {{allComp[3*(i-1)+j-1].name}}
                        </ElText>
                        <ElTag type="primary">{{allComp[3*(i-1)+j-1].state}}</ElTag>
                     </ElRow>
                  </template>
               </ElCard>
            </li>
         </ul>
      </ElTabPane>
      <ElTabPane label="个人" name="个人">个人</ElTabPane>
   </ElTabs>
</template>
<script setup lang="ts">

import { ref } from "vue"
import logo from "/logo.png"

let tabName = ref("首页");

const imgList = [
   {
      name: "logo",
      img: logo
   },
];

const news = [
   {
      title: "乒乓球赛事系统",
      date: "2024.1.1"
   },
   {
      title: "乒乓球赛事系统",
      date: "2024.1.1"
   },
   {
      title: "乒乓球赛事系统",
      date: "2024.1.1"
   },
];

const firstPageComp = [
   {
      name: "乒乓球赛事系统",
      date: "2024.1.1",
      cover: logo,
      state: "准备中"
   },
   {
      name: "乒乓球赛事系统",
      date: "2024.1.1",
      cover: logo,
      state: "进行中"
   },
   {
      name: "乒乓球赛事系统",
      date: "2024.1.1",
      cover: logo,
      state: "已结束"
   },
];

const notice = [
   {
      title: "乒乓球赛事系统",
      date: "2024.1.1"
   },
   {
      title: "乒乓球赛事系统",
      date: "2024.1.1"
   },
   {
      title: "乒乓球赛事系统",
      date: "2024.1.1"
   },
];

let allComp = [
   {
      name: "乒乓球赛事系统",
      date: "2024.1.1",
      cover: logo,
      state: "准备中"
   },
   {
      name: "乒乓球赛事系统",
      date: "2024.1.1",
      cover: logo,
      state: "进行中"
   },
   {
      name: "乒乓球赛事系统",
      date: "2024.1.1",
      cover: logo,
      state: "已结束"
   },
];

let compLineNum = ref(1);

let searchText = ref("")

</script>

<style scoped>
:deep(.el-tabs__header) {
   background-color: #ff4d00;
   margin: 0;
}

:deep(.el-tabs__item) {
   opacity: 0.8;
   background-color: aliceblue;
   width: 100px;
   font-size: large;
}

:deep(.el-tabs__content) {
   padding: 20px;
}

:deep(.el-input__wrapper) {
   padding-right: 0;
   padding-bottom: 0;
}

ul {
   list-style: none;
   padding: 0;
}

.el-card {
   width: 360px;
   height: 280px;
}

:deep(.el-card__body) {
   width: 320px;
   height: 180px;
}
</style>