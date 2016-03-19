import vk_api


vk = vk_api.VkApi(login="dima1da@yandex.ru",password="rekord777888")

vk.authorization()

tools = vk_api.VkTools(vk)


a = vk.method("groups.get")
print(a)