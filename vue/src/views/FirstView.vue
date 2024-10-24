<template>
  <video ref="entryVideo" :src="entryAnim" autoplay muted></video>
  <Transition name="el-fade-in-linear">
    <div v-if="panelName == 'login'" class="firstPanel">
      <ElForm label-width="auto">
        <ElFormItem label="用户名" style="margin-top: 80px;">
          <ElInput v-model="userInfo.userName" />
        </ElFormItem>
        <ElFormItem label="密码" style="margin-top: 50px;">
          <ElInput type="password" v-model="userInfo.password" show-password />
        </ElFormItem>
        <ElFormItem style="margin-top: 50px;">
          <div style="width: 100%;text-align: center;">
            <ElButton type="primary" v-on:click="login">登录</ElButton>
            <ElButton v-on:click="changePanel('userInfo1')">注册</ElButton>
          </div>
        </ElFormItem>
        <ElFormItem size="small">
          <div style="width: 100%;text-align: center;">
            <ElButton link size="small" v-on:click="changePanel('changePassword')">修改密码</ElButton>
          </div>
        </ElFormItem>
      </ElForm>
    </div>
  </Transition>
  <Transition name="el-fade-in-linear">
    <div v-if="panelName == 'userInfo1'" class="firstPanel">
      <ElForm label-width="auto">
        <ElFormItem label="姓名" style="margin-top: 30px;">
          <ElInput v-model="userInfo.name" />
        </ElFormItem>
        <ElFormItem label="性别" style="margin-top: 30px;">
          <ElRadioGroup v-model="userInfo.sex">
            <ElRadio value="男">男</ElRadio>
            <ElRadio value="女">女</ElRadio>
          </ElRadioGroup>
        </ElFormItem>
        <ElFormItem label="出生日期" style="margin-top: 30px;">
          <ElDatePicker v-model="userInfo.birthday" type="date"></ElDatePicker>
        </ElFormItem>
        <ElFormItem label="籍贯" style="margin-top: 30px;">
          <ElInput v-model="userInfo.birthPlace" />
        </ElFormItem>
        <ElFormItem label="乒乓球运动水平等级" label-width="140px" style="margin-top: 30px;">
          <ElSelect v-model="userInfo.TTL">
            <ElOption v-for="item in TTLs" :key="item" :label="item" :value="item">
            </ElOption>
          </ElSelect>
        </ElFormItem>
        <ElFormItem style="margin-top: 30px;">
          <div style="width: 100%;text-align: center;">
            <ElButton v-on:click="changePanel('login')">返回</ElButton>
            <ElButton type="primary" v-on:click="changePanel('userInfo2')">下一页</ElButton>
          </div>
        </ElFormItem>
      </ElForm>
    </div>
  </Transition>
  <Transition name="el-fade-in-linear">
    <div v-if="panelName == 'userInfo2'" class="firstPanel">
      <ElForm label-width="auto">
        <ElFormItem label="用户名" style="margin-top: 30px;">
          <ElInput v-model="userInfo.userName" />
        </ElFormItem>
        <ElFormItem label="密码" style="margin-top: 30px;">
          <ElInput type="password" v-model="userInfo.password" show-password />
        </ElFormItem>
        <ElFormItem label="确认密码" style="margin-top: 30px;">
          <ElInput type="password" v-model="confirmPassword" show-password />
        </ElFormItem>
        <ElFormItem label="邮箱" style="margin-top: 30px;">
          <ElInput type="email" v-model="userInfo.email" />
        </ElFormItem>
        <ElFormItem label="验证码" style="margin-top: 30px;">
          <ElInput v-model="VCode">
            <template #append>
              <ElButton v-on:click="getVC" v-bind:disabled="VCButton.disabled">{{ VCButton.text }}</ElButton>
            </template>
          </ElInput>
        </ElFormItem>
        <ElFormItem style="margin-top: 30px;">
          <div style="width: 100%;text-align: center;">
            <ElButton v-on:click="changePanel('userInfo1')">上一页</ElButton>
            <ElButton type="primary" v-on:click="register">注册</ElButton>
          </div>
        </ElFormItem>
      </ElForm>
    </div>
  </Transition>
  <Transition name="el-fade-in-linear">
    <div v-if="panelName == 'changePassword'" class="firstPanel">
      <ElForm label-width="auto">
        <ElFormItem label="用户名" style="margin-top: 40px;">
          <ElInput v-model="userInfo.userName" />
        </ElFormItem>
        <ElFormItem label="密码" style="margin-top: 40px;">
          <ElInput type="password" v-model="userInfo.password" show-password />
        </ElFormItem>
        <ElFormItem label="确认密码" style="margin-top: 40px;">
          <ElInput type="password" v-model="confirmPassword" show-password />
        </ElFormItem>
        <ElFormItem label="验证码" style="margin-top: 40px;">
          <ElInput v-model="VCode">
            <template #append>
              <ElButton v-on:click="getVC" v-bind:disabled="VCButton.disabled">{{ VCButton.text }}</ElButton>
            </template>
          </ElInput>
        </ElFormItem>
        <ElFormItem style="margin-top: 40px;">
          <div style="width: 100%;text-align: center;">
            <ElButton v-on:click="changePanel('login')">返回</ElButton>
            <ElButton type="primary" v-on:click="updatePassword">修改</ElButton>
          </div>
        </ElFormItem>
      </ElForm>
    </div>
  </Transition>
</template>

<script setup lang="ts">

import entryAnim from "@/assets/entryAnim.mp4";
import { ElMessage } from 'element-plus'
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { useInfoStore } from "@/stores/info";
import axios from "axios";

const router = useRouter();
const infoStore = useInfoStore();

let entryVideo = ref();
let panelName = ref("");
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
let confirmPassword = ref("");
let VCode = ref("");
let VCButton = ref({
  disabled: false,
  text: "获取验证码",
  duration: 60
});

onMounted(() => {
  entryVideo.value.addEventListener("ended", function () {
    panelName.value = "login";
  });
});

function login() {
  //get:userName+password=>用户名与密码是否正确+userId
  axios
    .get('/login',{
      params:{
        userName:userInfo.value.userName,
        password:userInfo.value.password
      }
    })
    .then(res=>{
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
  // let flag = true;
  // let userId = 0;
  // if (flag) {
  //   ElMessage.success("登录成功");
  //   infoStore.userId = 0;
  //   router.push("/home");
  // }
  // else {
  //   ElMessage.error("用户名或密码错误");
  // }
}

function changePanel(name: string) {
  panelName.value = name;
}

function getVC() {
  //get:email=>是否发送成功
  axios
    .get('')
    .then()
    .catch()
  let flag = true;
  if (flag) {
    ElMessage.success("发送成功");
    VCButton.value.disabled = true;
    let time = VCButton.value.duration;
    let timer = setInterval(function () {
      if (time > 0) {
        VCButton.value.text = time + "秒后启用";
        time--;
      }
      else {
        VCButton.value.disabled = false;
        VCButton.value.text = "获取验证码";
        clearInterval(timer);
      }
    }, 1000);
  }
  else {
    ElMessage.error("发送失败");
  }
}

function register() {
  //post:用户信息+验证码=>是否注册成功+userId
  axios
    .post('')
    .then()
    .catch()
  let flag = true;
  let userId = 0;
  if (flag) {
    ElMessage.success("注册成功");
    infoStore.userId = userId;
    router.push("/home")
  }
  else {
    ElMessage.error("注册失败");
  }
}

function updatePassword() {
  //put:旧密码+新密码=>是否修改成功
  axios
    .put('')
    .then()
    .catch()
  let flag = true;
  if (flag) {
    ElMessage.success("修改成功");
  }
  else {
    ElMessage.error("修改失败");
  }
}

</script>

<style scoped>
.firstPanel {
  background-color: lightgray;
  width: 300px;
  height: 400px;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  border-radius: 10px;
  padding: 20px;
}

video {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  object-fit: fill;
  width: 100%;
  height: 100%;
  z-index: -1;
}
</style>
