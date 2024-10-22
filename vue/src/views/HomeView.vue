<template>
   <ElTabs type="card">
      <ElTabPane label="首页">
         <ElRow justify="space-evenly">
            <ElCarousel height="320px" style="width: 640px;">
               <ElCarouselItem v-for="item in lastComps" :key="item.id">
                  <img :src="item.cover" style="height:100%;width: 100%;object-fit: contain;" v-on:click="selectRole(item.id,item.name)"/>
               </ElCarouselItem>
            </ElCarousel>
            <ElTable :data="news" height="320px" style="width: 640px;" v-on:row-click="getNews">
               <ElTableColumn prop="title" label="新闻"></ElTableColumn>
               <ElTableColumn prop="date" label="发布日期" width="100px"></ElTableColumn>
            </ElTable>
         </ElRow>
         <ElRow justify="space-evenly">
            <ElTable :data="lastComps" height="320px" style="width: 640px;" v-on:row-click="compsTableRowClick">
               <ElTableColumn prop="name" label="赛事"></ElTableColumn>
               <ElTableColumn prop="date" label="举办日期" width="100px"></ElTableColumn>
            </ElTable>
            <ElTable :data="notices" height="320px" style="width: 640px;" v-on:row-click="getNotice">
               <ElTableColumn prop="title" label="公告"></ElTableColumn>
               <ElTableColumn prop="date" label="发布日期" width="100px"></ElTableColumn>
            </ElTable>
         </ElRow>
      </ElTabPane>
      <ElTabPane label="赛事" lazy>
         <ElRow justify="center">
            <ElInput v-model="searchText" style="width: 400px;margin-bottom: 20px;">
               <template #suffix>
                  <ElButton type="primary" v-on:click="searchComps">搜索</ElButton>
               </template>
            </ElInput>
         </ElRow>
         <CompList :comps="comps" :scroll-disabled="scrollDisabled" v-on:load="loadComps" v-on:cardClick="selectRole">
         </CompList>
      </ElTabPane>
      <ElTabPane label="个人">
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
                        <ElButton v-show="fixing" type="primary" v-on:click="updateUserInfo">确认</ElButton>
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
                                 <ElButton type="primary" v-on:click="queryComp">查询</ElButton>
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
                  <ElButton v-on="manageComp">确认</ElButton>
               </div>
               <ElDialog v-model="addDialog" title="增加参与人员" width="500">
                  <ElForm>
                     <ElFormItem label="参与人员">
                        <ElInput v-model="addedName" />
                     </ElFormItem>
                     <ElFormItem label="参与身份">
                        <ElSelect v-model="addedIdentity">
                           <ElOption label="运动员" value="运动员" />
                           <ElOption label="裁判员" value="裁判员" />
                     </ElSelect>
                     </ElFormItem>
                  </ElForm>
                  <template #footer>
                     <div class="dialog-footer">
                        <ElButton v-on:click="addDialog = false">取消</ElButton>
                        <ElButton type="primary" v-on:click="addParticipant">
                           确认
                        </ElButton>
                     </div>
                  </template>
               </ElDialog>
            </ElTabPane>
         </ElTabs>
      </ElTabPane>
   </ElTabs>
   <ElDialog v-model="roleDialog" title="选择身份" width="500">
            <ElSelect v-model="role">
               <ElOption label="赛事管理员" value="赛事管理员" />
               <ElOption label="裁判员" value="裁判员" />
               <ElOption label="观众" value="观众" />
            </ElSelect>
      <template #footer>
         <div class="dialog-footer">
            <ElButton v-on:click="roleDialog = false">取消</ElButton>
            <ElButton type="primary" v-on:click="openComp">
               确认
            </ElButton>
         </div>
      </template>
   </ElDialog>
</template>
<script setup lang="ts">

import { onMounted, ref } from "vue";
import { ElMessage } from "element-plus";
import logo from "/logo.png";
import CompList from "@/components/CompList.vue";
import { useRouter } from 'vue-router'
import { useInfoStore } from "@/stores/info";

const router = useRouter();
const infoStore = useInfoStore();

let lastComps: any[] = [];

let news = [
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

let notices = [
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

let comps = ref([
   {
      id: 0,
      name: "乒乓球赛事0",
      date: "2024.1.1",
      cover: logo,
      state: "准备中"
   },
   {
      id: 1,
      name: "乒乓球赛事1",
      date: "2024.1.1",
      cover: logo,
      state: "进行中"
   },
   {
      id: 2,
      name: "乒乓球赛事2",
      date: "2024.1.1",
      cover: logo,
      state: "已结束"
   },
])
let searchText = ref("");

let scrollDisabled = ref(false);

let userInfo = ref({
   userId: 0,
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

let aboutComps = [
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

function changeRow(row: any) {
   currentIndex = participants.value.indexOf(row);
}

let roleDialog = ref(false)

let role = ref("")

let compName = ""

onMounted(() => {
   //get:用户id=>最近6个赛事+所有新闻+所有公告+用户信息+用户参赛信息
   lastComps = [];
   news = [];
   notices = [];
   userInfo.value;
   aboutComps = [];
});

function compsTableRowClick(row:any){
   let comp = lastComps.find(item => item.name=row.name);
   selectRole(comp.id,comp.name);
}

function getNews(row:any){
   news.find(item => item.title=row.title);
   //get:新闻id=>新闻
}

function getNotice(row:any){
   notices.find(item => item.title=row.title);
   //get:通知id=>通知
}

function searchComps(){
   //loadComps
}

function loadComps() {
   //get:赛事id+搜索文本=>赛事名称或赛事状态中含有搜索文本的比该比赛晚创建的最近6个赛事
   //get:搜索文本=>赛事名称或赛事状态中含有搜索文本的最近6个赛事
   //get:赛事id=>比该比赛晚创建的最近6个赛事
   if (comps.value.length > 20) {
      scrollDisabled.value = true;
      return;
   }
   comps.value.push({
      id: comps.value.length,
      name: "乒乓球赛事" + comps.value.length,
      date: "2024.1.1",
      cover: logo,
      state: "准备中"
   },
      {
         id: comps.value.length + 1,
         name: "乒乓球赛事" + (comps.value.length + 1),
         date: "2024.1.1",
         cover: logo,
         state: "进行中"
      },
      {
         id: comps.value.length + 2,
         name: "乒乓球赛事" + (comps.value.length + 2),
         date: "2024.1.1",
         cover: logo,
         state: "已结束"
      });
}

function selectRole(id:number,name:string){
   infoStore.compId=id;
   compName=name;
   roleDialog.value=true;
}

function openComp() {
   //get:赛事id+用户id+role=>是否存在身份
   infoStore.role = role.value;
   let flag = true;
   if(flag){
      ElMessage.success("进入赛事");
      router.push({
      name: 'comp',
      params: {
         compName: compName,
      }
   });
   }
   else{
      ElMessage.error("无法进入");
   }
   

}


function updateUserInfo() {
   //put:用户信息=>是否成功
   fixing.value = false;
   let flag = true;
   if(flag){
      ElMessage.success("修改成功");
      userInfo.value = userInfoCopy;
   }
   else{
      ElMessage.error("修改失败");
   }
}

function queryComp(){
   //get:用户id+赛事名称=>以该用户为赛事管理员的赛事信息和参与人员
}

function deleteParticipants() {
   if (currentIndex) {
      participants.value.splice(currentIndex, 1);
   }
}

function addParticipant() {
   participants.value.push({
      name: addedName.value,
      identity: addedIdentity.value
   });
   addDialog.value = false;
}

function manageComp(){
   //post:赛事信息+role表=>根据赛事是否存在及其状态，创建或修改赛事信息+根据role数量是否增减增或删role表，返回是否成功
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
</style>