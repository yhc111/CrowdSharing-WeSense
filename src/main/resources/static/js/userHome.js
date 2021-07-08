/**
 * 用户主页vue
 */

const vm = new Vue({
    el: "#app",
    data() {
        const validatePass = (rule, value, callback) => {
            if (value === '') {
                return callback(new Error('please enter your old password'));
            }
            else if (value.length < 6) {
                return callback(new Error('your password must be at least 6 characters long'));
            }
            else {
                callback();
            }
        };
        const validateRepeatPass = (rule, value, callback) => {
            if (value === '') {
                return callback(new Error('please enter your password'));
            } else if (value !== this.pwd_form.new_pwd) {
                return callback(new Error('The two passwords you typed do not match'))
            }
            callback();
        };
        return {
            rules: {
                old_pwd: [{validator: validatePass, trigger: 'blur'}],
                new_pwd: [{validator: validatePass, trigger: 'blur'}],
                repeat_pwd: [{validator: validateRepeatPass, trigger: 'blur'}]
            },
            active_page: 1,
            userInfo: {
                username: "user",
                realName: "user",
                birthday: "yy-mm-dd",
                phone: "12345678",
                email: "foo@bar.com",
                level: 0
            },
            imageUrl: "",
            info_form:{
                username: "",
                realName: "",
                birthday: "",
                phone: "",
                email: "",
                level: 0
            },
            pwd_form: {
                old_pwd: "",
                new_pwd: "",
                repeat_pwd: ""
            }
        }
    },
    mounted: function () {
        const this_ = this;
        $.ajax({
            type: "get",
            url: "http://localhost:8080/user/getUserInfo",
            async: true,
            success: function (res) {
                this_.userInfo = res
            },
            error: function () {
                console.log("获取用户信息失败");
            }
        });
    },
    methods: {
        switch_page: function (index) {
            if (this.active_page === index)
                return
            this.active_page = index
            // console.log("switch to page " + this.active_page)
        }
    }
});
