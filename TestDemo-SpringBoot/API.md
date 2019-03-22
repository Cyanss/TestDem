# Test工程API

 
```
主机   ip: 192.168.1.184
端口   port: 8083
根路径 root-path: /test
子路径 son-path: 
  用户    user：/user
  插件    pluhin: /plugin
```

## 用户（user）相关接口 
```
exp: http://192.168.1.184:8083/test/user/
```
### 查询一条用户信息
```$xslt
GET: /one 
params: userid

http://192.168.1.184:8083/test/user/one?userid=5
return:
    {
        "code": 0,
        "msg": "成功",
        "data": {
            "username": "Cyan3",
            "phone": "13298359897",
            "email": "snow22314@outlook.com",
            "password": "*password",
            "auth": "Level3",
            "createtime": "2019-03-12T07:53:04.000+0000"
        }
    }
```

### 通过用户名查询
```$xslt
GET: /name 
params: username

http://192.168.1.184:8083/test/user/name?username=Cyan6
return:
    {
        "code": 0,
        "msg": "成功",
        "data": {
            "username": "Cyan6",
            "phone": "13298359897",
            "email": "snow22314@outlook.com",
            "password": "*password",
            "auth": "Level6",
            "createtime": "2019-03-12T07:53:04.000+0000"
        }
    }
```
### 查询所有（分页）
```$xslt
GET: /page 
params: 
    page (默认0)
    size (默认10)

http://192.168.1.184:8083/test/user/page
return:
    {
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "username": "Cyan0",
            "phone": "13298359897",
            "email": "snow22314@outlook.com",
            "password": "*password",
            "auth": "Level0",
            "createtime": "2019-03-12T07:53:04.000+0000"
        },
        {
            "username": "Cyan1",
            "phone": "13298359897",
            "email": "snow22314@outlook.com",
            "password": "*password",
            "auth": "Level1",
            "createtime": "2019-03-12T07:53:04.000+0000"
        },
       ……
       ……
       ……
    }
       
```
### 增加用户信息
```
POST: /add
params: 无
body: 
    userName（不为空）
    userPhone（不为空）
    userEmail
    userPassword
    userAuth

http://192.168.1.184:8083/test/user/add
body:
{
	"userName":"Cyan",
	"userPhone":"13298359897",
	"userEmail":"snow22314@outlook.com",
	"userPassword":"*password",
	"userAuth": "Level"
}
return:
    {
        "code": 0,
        "msg": "添加成功",
        "data": {
            "userId": 4,
            "userName": "Cyan2",
            "userPhone": "13298359897",
            "userEmail": "snow22314@outlook.com",
            "userPassword": "*password"
        }
    }
```
### 删除用户信息
```
DELETE: /delete
params: userid

http://192.168.1.184:8083/test/user/delete?userid=5
return:
{
    "code": 7,
    "msg": "请先删除该用户的上传的插件信息"
}

```
### 修改用户信息
```
PUT: /update
params: userid
body: 
    userName（不为空）
    userPhone（不为空）
    userEmail
    userPassword
    userAuth
    (改啥传啥)
    
http://192.168.1.184:8083/test/user/update?userid=5    
return:
{
    "code": 0,
    "msg": "更新成功",
    "data": {
        "userId": 5,
        "userName": "Cyan3",
        "userPhone": "13298359897",
        "userEmail": "snow22314@outlook.com",
        "userPassword": "*password",
        "userAuth": "Level",
        "createTime": "2019-03-12T07:53:04.000+0000",
        "updateTime": "2019-03-12T08:25:31.000+0000"
    }
}

```

## 插件（plugin）相关接口 
```
exp: http://192.168.1.184:8083/test/plugin/
```
### 增加插件信息
```
POST: /add
params: 无
body: 
    userId （不为空）
    pluginName（不为空）
    pluginType（不为空）
    pluginIcon
    pluginEnv
    pluginKeyword（不为空）
    pluginUrl
    pluginContent

http://192.168.1.184:8083/test/plugin/add
body:
    {
	"pluginName":"Plugin",
	"userId": 9,
	"pluginType":"type",
	"pluginKeyword":"key",
	"pluginContent":"插件描述",
	"pluginEnv": "环境"
    }
return:
    {
    "code": 0,
    "msg": "添加成功",
    "data": {
        "pluginId": 23,
        "userId": 9,
        "pluginName": "Plugin",
        "pluginType": "type",
        "pluginEnv": "环境",
        "pluginKeyword": "key",
        "pluginContent": "插件描述"
        }
    }
```
### 删除插件信息
```
DELETE: /delete
params:pluginid
http://192.168.1.184:8083/test/plugin/delete?pluginid=5
return:
{
    "code": 0,
    "msg": "删除成功"
}

```
### 修改用户信息
```
PUT: /update
params: pluginid
body: 
    userId （不为空）
    pluginName（不为空）
    pluginType（不为空）
    pluginIcon
    pluginEnv
    pluginKeyword（不为空）
    pluginUrl
    pluginContent
    (改啥传啥)
    
http://192.168.1.184:8083/test/plugin/update?pluginid=9
return:
{
    "code": 0,
    "msg": "更新成功",
    "data": {
        "pluginId": 9,
        "userId": 7,
        "pluginName": "Plugin5",
        "pluginType": "Type5",
        "pluginIcon": "//fanyi.bdstatic.com/static/translation/widget/translate/details/dictionary/img/kingsoft_2x_19c9bd0.png",
        "pluginEnv": "Windows",
        "pluginKeyword": "p",
        "pluginUrl": "http://www.baidu.com",
        "pluginContent": "插件5",
        "createTime": "2019-03-12T09:10:10.000+0000",
        "updateTime": "2019-03-12T09:10:10.000+0000"
    }
}

```
### 查询一条插件信息
```$xslt
GET: /one 
params: pluginid

http://192.168.1.184:8083/test/plugin/one?pluginid=19
return:
   {
       "code": 0,
       "msg": "成功",
       "data": {
           "pluginid": 19,
           "pluginname": "Plugin15",
           "type": "Type15",
           "icon": "//fanyi.bdstatic.com/static/translation/widget/translate/details/dictionary/img/kingsoft_2x_19c9bd0.png",
           "env": "Windows",
           "keyword": "p",
           "url": "http://www.baidu.com",
           "content": "插件15",
           "createtime": "2019-03-12T09:10:11.000+0000"
       }
   }
```

### 通过插件id查询所有详细信息
```$xslt
GET: /all 
params: pluginid

http://192.168.1.184:8083/test/plugin/all?pluginid=10
return:
    {
        "code": 0,
        "msg": "成功",
        "data": {
            "pluginid": 10,
            "pluginname": "Plugin6",
            "type": "Type6",
            "icon": "//fanyi.bdstatic.com/static/translation/widget/translate/details/dictionary/img/kingsoft_2x_19c9bd0.png",
            "env": "Windows",
            "keyword": "p",
            "url": "http://www.baidu.com",
            "content": "插件6",
            "plugincreate": "2019-03-12T09:10:10.000+0000",
            "userid": 8,
            "username": "Cyan6",
            "phone": "13298359897",
            "email": "snow22314@outlook.com",
            "password": "*password",
            "auth": "Level6",
            "usercreate": "2019-03-12T09:09:49.000+0000"
        }
    }
```
### 查询所有（分页）
```$xslt
GET: /page 
params: 
    page (默认0)
    size (默认10)

http://192.168.1.184:8083/test/plugin/page
return:
    
    {
        "code": 0,
        "msg": "成功",
        "data": [
            {
                "pluginid": 4,
                "pluginname": "Plugin0",
                "type": "Type0",
                "icon": "//fanyi.bdstatic.com/static/translation/widget/translate/details/dictionary/img/kingsoft_2x_19c9bd0.png",
                "env": "Windows",
                "keyword": "p",
                "url": "http://www.baidu.com",
                "content": "插件0",
                "createtime": "2019-03-12T09:10:10.000+0000"
            },
            {
                "pluginid": 6,
                "pluginname": "Plugin2",
                "type": "Type2",
                "icon": "//fanyi.bdstatic.com/static/translation/widget/translate/details/dictionary/img/kingsoft_2x_19c9bd0.png",
                "env": "Windows",
                "keyword": "p",
                "url": "http://www.baidu.com",
                "content": "插件2",
                "createtime": "2019-03-12T09:10:10.000+0000"
            }
            ...
            ...
            ...
        ]
    }   
```
### 通过插件名模糊查找
```$xslt
GET: /like
params: pluginname
        page (默认0)
        size (默认10)

http://192.168.1.184:8083/test/plugin/like?pluginname=插
return:
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "pluginid": 24,
            "pluginname": "插件0",
            "type": "Type0",
            "icon": "//fanyi.bdstatic.com/static/translation/widget/translate/details/dictionary/img/kingsoft_2x_19c9bd0.png",
            "env": "Windows",
            "keyword": "p",
            "url": "http://www.baidu.com",
            "content": "插件0",
            "createtime": "2019-03-12T09:59:54.000+0000"
        },
        {
            "pluginid": 25,
            "pluginname": "插件1",
            "type": "Type1",
            "icon": "//fanyi.bdstatic.com/static/translation/widget/translate/details/dictionary/img/kingsoft_2x_19c9bd0.png",
            "env": "Windows",
            "keyword": "p",
            "url": "http://www.baidu.com",
            "content": "插件1",
            "createtime": "2019-03-12T09:59:54.000+0000"
        }
        ...
        ...
        ...
    ]
}
```
### 通过插件编号查询用户信息
```$xslt
GET: /like
params: pluginid
http://192.168.1.184:8083/test/plugin/user?pluginid=8
return:
   {
       "code": 0,
       "msg": "成功",
       "data": {
           "username": "Cyan903",
           "phone": "13298359897",
           "email": "snow22314@outlook.com",
           "password": "*password",
           "auth": "Level4",
           "createtime": "2019-03-12T09:09:49.000+0000"
       }
   }
```