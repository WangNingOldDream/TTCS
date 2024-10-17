<template>
   <ElTabs type="card" v-model="mainTab">
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
            <ElTable :data="firstPageComps" height="320px" style="width: 640px;background-color: aqua;">
               <ElTableColumn prop="name" label="赛事"></ElTableColumn>
               <ElTableColumn prop="date" label="举办日期" width="100px"></ElTableColumn>
            </ElTable>
            <ElTable :data="notice" height="320px" style="width: 640px;background-color: aqua;">
               <ElTableColumn prop="title" label="公告"></ElTableColumn>
               <ElTableColumn prop="date" label="发布日期" width="100px"></ElTableColumn>
            </ElTable>
         </ElRow>
      </ElTabPane>
      <ElTabPane label="赛事" name="赛事" lazy>
         <ElRow justify="center">
            <ElInput v-model="searchText" style="width: 400px;margin-bottom: 20px;">
               <template #suffix>
                  <ElButton type="primary">搜索</ElButton>
               </template>
            </ElInput>
         </ElRow>
         <ul class="no-style-ul" ref="scroll" v-infinite-scroll="load" :infinite-scroll-disabled="scrollDisabled"
            infinite-scroll-distance="10" style="height:580px;margin-bottom: 0;overflow: auto;">
            <li v-for="i in compLineNum" :key="i" style="display: flex;justify-content: space-evenly;">
               <ElCard v-for="j in i == compLineNum ? searchedComps.length - 3 * (i - 1) : 3" :key="j" shadow="hover">
                  <img :src="searchedComps[3 * (i - 1) + j - 1].cover" style="width: 100%;height: 100%;" />
                  <template #footer>
                     <ElRow justify="center">
                        <ElText style="width: 200px;" truncated size="large">
                           {{ searchedComps[3 * (i - 1) + j - 1].name }}
                        </ElText>
                        <ElTag :type="elTagType[searchedComps[3 * (i - 1) + j - 1].state as keyof typeof elTagType]">
                           {{ searchedComps[3 * (i - 1) + j - 1].state }}</ElTag>
                     </ElRow>
                  </template>
               </ElCard>
            </li>
         </ul>
      </ElTabPane>
      <ElTabPane label="个人" name="个人">
         <ElTabs tab-position="left" type="card">
            <ElTabPane label="个人信息" style="display: flex;justify-content: center;">
               <ElForm label-width="auto" style="width: 400px;">
                  <ElFormItem label="头像">
                     <ElAvatar shape="square" :size="60" :src="logo" />
                  </ElFormItem>
                  <ElFormItem label="姓名" style="margin-top: 30px;">
                     <ElInput v-model="userInfo.name" :disabled="!fixing" />
                  </ElFormItem>
                  <ElFormItem label="性别" style="margin-top: 30px;">
                     <ElRadioGroup v-model="userInfo.sex" :disabled="!fixing">
                        <ElRadio value="男">男</ElRadio>
                        <ElRadio value="女">女</ElRadio>
                     </ElRadioGroup>
                  </ElFormItem>
                  <ElFormItem label="出生日期" style="margin-top: 30px;">
                     <ElDatePicker v-model="userInfo.birthday" type="date" :disabled="!fixing"></ElDatePicker>
                  </ElFormItem>
                  <ElFormItem label="籍贯" style="margin-top: 30px;">
                     <ElInput v-model="userInfo.birthPlace" :disabled="!fixing" />
                  </ElFormItem>
                  <ElFormItem label="乒乓球运动水平等级" label-width="140px" style="margin-top: 30px;">
                     <ElSelect v-model="userInfo.TTL" :disabled="!fixing">
                        <ElOption v-for="item in TTLs" :key="item" :label="item" :value="item">
                        </ElOption>
                     </ElSelect>
                  </ElFormItem>
                  <ElFormItem label="用户名" style="margin-top: 30px;">
                     <ElInput v-model="userInfo.userName" :disabled="!fixing" />
                  </ElFormItem>
                  <ElFormItem label="密码" style="margin-top: 30px;">
                     <ElInput type="password" v-model="userInfo.password" show-password disabled />
                  </ElFormItem>
                  <ElFormItem label="邮箱" style="margin-top: 30px;">
                     <ElInput type="email" v-model="userInfo.email" disabled />
                  </ElFormItem>
                  <ElFormItem style="margin-top: 30px;">
                     <div style="width: 100%;text-align: center;">
                        <ElButton v-show="!fixing" type="primary" v-on:click="fixBtnClick">修改</ElButton>
                        <ElButton v-show="fixing" type="primary" v-on:click="fixConfirm">确认</ElButton>
                        <ElButton v-show="fixing" v-on:click="fixCancel">取消</ElButton>
                     </div>
                  </ElFormItem>
               </ElForm>
            </ElTabPane>
            <ElTabPane label="参赛信息">
               <ElTable :data="aboutComps">
                  <ElTableColumn prop="name" label="赛事名称"></ElTableColumn>
                  <ElTableColumn prop="identity" label="参与身份"></ElTableColumn>
                  <ElTableColumn prop="date" label="举办时间"></ElTableColumn>
                  <ElTableColumn prop="place" label="举办地点"></ElTableColumn>
                  <ElTableColumn prop="state" label="状态"></ElTableColumn>
               </ElTable>
            </ElTabPane>
            <ElTabPane label="编辑赛事">
               <ElRow justify="space-evenly" style="margin-top: 20px;">
                  <ElCol :span="10">
                     <ElForm>
                        <ElFormItem label="赛事名称">
                           <ElInput v-model="editedComp.name">
                              <template #suffix>
                                 <ElButton type="primary">查询</ElButton>
                              </template>
                           </ElInput>
                        </ElFormItem>
                        <ElFormItem label="举办时间">
                           <ElDatePicker type="date" v-model="editedComp.date"></ElDatePicker>
                           <ElTimePicker v-model="editedComp.time"></ElTimePicker>
                        </ElFormItem>
                        <ElFormItem label="举办地点">
                           <ElInput v-model="editedComp.place"></ElInput>
                        </ElFormItem>
                        <ElFormItem label="赛制">
                           <ElSelect style="width: 200px;" v-model="editedComp.format">
                              <ElOption v-for="item in compFormats" :key="item" :label="item" :value="item">
                              </ElOption>
                           </ElSelect>
                        </ElFormItem>
                        <ElFormItem label="是否允许观赛">
                           <ElRadioGroup v-model="editedComp.allowWatch">
                              <ElRadio value="是">是</ElRadio>
                              <ElRadio value="否">否</ElRadio>
                           </ElRadioGroup>
                        </ElFormItem>
                        <ElFormItem label="是否允许报名">
                           <ElRadioGroup v-model="editedComp.allowRegister">
                              <ElRadio value="是">是</ElRadio>
                              <ElRadio value="是">否</ElRadio>
                           </ElRadioGroup>
                        </ElFormItem>
                        <ElFormItem label="运动员人数范围">
                           <ElInputNumber v-model="editedComp.minPlayerNum"></ElInputNumber>
                           ~
                           <ElInputNumber v-model="editedComp.maxPlayerNum"></ElInputNumber>
                        </ElFormItem>
                        <ElFormItem label="运动员性别限制">
                           <ElRadioGroup v-model="editedComp.sexLimit">
                              <ElRadio value="男">男</ElRadio>
                              <ElRadio value="女">女</ElRadio>
                              <ElRadio value="不限">不限</ElRadio>
                           </ElRadioGroup>
                        </ElFormItem>
                        <ElFormItem label="运动员年龄范围">
                           <ElInputNumber v-model="editedComp.minPlayerAge"></ElInputNumber>
                           ~
                           <ElInputNumber v-model="editedComp.maxPlayerAge"></ElInputNumber>
                        </ElFormItem>
                        <ElFormItem label="运动员运动水平范围">
                           <ElSelect style="width: 120px;" v-model="editedComp.minTTCLevel">
                              <ElOption v-for="item in TTLs" :key="item" :label="item" :value="item">
                              </ElOption>
                           </ElSelect>
                           ~
                           <ElSelect style="width: 120px;" v-model="editedComp.maxTTCLevel">
                              <ElOption v-for="item in TTLs" :key="item" :label="item" :value="item">
                              </ElOption>
                           </ElSelect>
                        </ElFormItem>
                     </ElForm>
                  </ElCol>
                  <ElCol :span="10">
                     <ElForm>
                        <ElFormItem label="赛事封面">
                           <img :src="editedComp.cover" style="width: 320px;height: 180px;" />
                        </ElFormItem>
                        <ElFormItem>
                           <ElTable :data="participants" style="height: 280px;" highlight-current-row
                              v-on:current-change="changeRow">
                              <ElTableColumn type="index" :index="1">
                              </ElTableColumn>
                              <ElTableColumn prop="name" label="参与人员"></ElTableColumn>
                              <ElTableColumn prop="identity" label="参与身份"></ElTableColumn>
                           </ElTable>
                        </ElFormItem>
                        <ElFormItem>
                           <div style="width: 100%;text-align: center;">
                              <ElButton v-on:click="addDialog = true">增加</ElButton>
                              <ElButton v-on:click="deleteParticipants">删除</ElButton>
                           </div>
                        </ElFormItem>
                     </ElForm>
                  </ElCol>
               </ElRow>
               <div style="width: 100%;margin-top: 10px;text-align: center;">
                  <ElButton>确认</ElButton>
               </div>
               <el-dialog v-model="addDialog" title="增加参与人员" width="500">
                  <el-form>
                     <el-form-item label="参与人员">
                        <el-input v-model="addedName" />
                     </el-form-item>
                     <el-form-item label="参与身份">
                        <el-select v-model="addedIdentity">
                           <el-option label="运动员" value="运动员" />
                           <el-option label="裁判员" value="裁判员" />
                        </el-select>
                     </el-form-item>
                  </el-form>
                  <template #footer>
                     <div class="dialog-footer">
                        <el-button v-on:click="addDialog = false">取消</el-button>
                        <el-button type="primary" v-on:click="addParticipant">
                           确认
                        </el-button>
                     </div>
                  </template>
               </el-dialog>
            </ElTabPane>
         </ElTabs>
      </ElTabPane>
   </ElTabs>
</template>
<script setup lang="ts">

import { ref } from "vue";
import logo from "/logo.png";
import type { EpPropMergeType } from "element-plus/es/utils/vue/props/types";

type TagType = EpPropMergeType<StringConstructor, "primary" | "success" | "danger" | "warning" | "info", unknown>;

let mainTab = ref("首页");

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

const firstPageComps = [
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

let searchedComps = ref([
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
]);

let compLineNum = 3;

let searchText = ref("");

let scrollDisabled = ref(false);

const elTagType = {
   "准备中": "primary" as TagType,
   "进行中": "success" as TagType,
   "已结束": "danger" as TagType,
};

function load() {
   if (searchedComps.value.length > 20) {
      scrollDisabled.value = true;
      return;
   }
   searchedComps.value.push({
      name: "乒乓球赛事系统" + compLineNum,
      date: "2024.1.1",
      cover: logo,
      state: "准备中"
   },
      {
         name: "乒乓球赛事系统" + compLineNum,
         date: "2024.1.1",
         cover: logo,
         state: "进行中"
      },
      {
         name: "乒乓球赛事系统" + compLineNum,
         date: "2024.1.1",
         cover: logo,
         state: "已结束"
      });
   compLineNum = Math.ceil(searchedComps.value.length / 3);
}

let userInfo = ref({
   userName: "",
   password: "",
   name: "",
   sex: "",
   birthday: "",
   birthPlace: "",
   TTL: "",
   email: ""
});

const TTLs = ["四级", "五级", "六级", "七级", "八级", "九级"];

let fixing = ref(false);

let userInfoCopy = userInfo.value;

const aboutComps = [
   {
      name: "乒乓球赛事系统",
      identity: "赛事管理员",
      date: "2024.1.1",
      place: "陕西师范大学",
      state: "准备中"
   },
   {
      name: "乒乓球赛事系统",
      identity: "运动员",
      date: "2024.1.1",
      place: "陕西师范大学",
      state: "进行中"
   },
   {
      name: "乒乓球赛事系统",
      identity: "裁判员",
      date: "2024.1.1",
      place: "陕西师范大学",
      state: "已结束"
   },
];

function fixBtnClick() {
   fixing.value = true;
   userInfoCopy = userInfo.value;
}

function fixConfirm() {
   fixing.value = false;
}

function fixCancel() {
   fixing.value = false;
   userInfo.value = userInfoCopy;
}

let editedComp = ref({
   cover: logo,
   name: "",
   date: "",
   time: "",
   place: "",
   format: "五局三胜",
   allowWatch: true,
   allowRegister: true,
   maxPlayerNum: 0,
   minPlayerNum: 0,
   sexLimit: "不限",
   maxPlayerAge: 0,
   minPlayerAge: 0,
   maxTTCLevel: 0,
   minTTCLevel: 0,
})

const compFormats = ["五局三胜", "七局四胜"];

let participants = ref([
   {
      name: "1",
      identity: "运动员"
   },
   {
      name: "2",
      identity: "运动员"
   },
   {
      name: "3",
      identity: "裁判员"
   }
]);

let addedName = ref("");
let addedIdentity = ref("")

let addDialog = ref(false)

let currentIndex: number | undefined = undefined;

function addParticipant() {
   participants.value.push({
      name: addedName.value,
      identity: addedIdentity.value
   });
   addDialog.value = false;
}

function changeRow(row: any) {
   currentIndex = participants.value.indexOf(row);
}

function deleteParticipants() {
   if (currentIndex) {
      participants.value.splice(currentIndex, 1);
   }
}


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

.noneStyleUl {
   list-style: none;
   padding: 0;
}

.el-card {
   width: 360px;
   height: 280px;
   margin-bottom: 10px;
}

:deep(.el-card__body) {
   width: 320px;
   height: 180px;
}
</style>