# 1-Part.CleanArchitecture
#### **```Separation of concerns, Recommended app architecture```**
---------------------

#### *Siz ko'rib turgan projectimda, authorization uchun simple project qilingan. Ma'lumotlarni saqlash uchun SharedPreferencedan foydalanilgan. Bunda oddiygina yondashilgan, Design patternlar va Dependency Injection framworklaridan foydalanilmasdan clean architecturaga kirish qilganman, chunki vazifamiz layerlarga ajratish.*
-------------------------------------

<img width="360" height="480" alt="Screen Shot 2023-04-09 at 12 58 44" src="https://user-images.githubusercontent.com/77477995/230761313-5dcf0953-ce38-409b-94b2-570a55a865a5.png"> <img hspace="20" width="360" height="480" alt="Screen Shot 2023-04-15 at 12 47 53" src="https://user-images.githubusercontent.com/77477995/232197173-3e675faf-4722-424a-9799-f7538856613f.png">

*1 - rasm. Projectimdagi structura qatlamlarni ko'rishingiz va classlarimizning layerlarda qanday joylashgan diagrammasini ko'rishingiz mumkin.*

Android proyekt kattalashib yangi funksionalliklar qo'shilavergan sayin, proyektni oson boshqarish imkonini beruvchi, proyekt mustahkamligini oshiradigan va proyektni testdan o‘tkazishni osonlashtiradigan arxitekturani aniqlash muhim ahamiyatga ega [Clean architecture](https://developer.android.com/topic/architecture).
>
> Aytish kerak bo'lgan eng muhim tamoyil - ```concern``` larni ajratishdir. Ya'ni barcha kodimizni Activity yoki Fragmentga yozish keng tarqalgan xatodir. Ushbu UI-ga asoslangan classlar faqat UI va operatsion tizim bilan o'zaro ta'sirini boshqaradigan logikani o'z ichiga olishi kerak. Ushbu classlarni iloji boricha o'ziga tegishli bo'lmagan logikalarni ajratib olish orqali biz komponentlarning ```life cycle``` bilan bog'liq ko'plab muammolardan qochishimiz va ushbu classlarni testdan o'tkazishni osonlashtirishimiz mumkin. 

Clean architektura, boshqa qatlamlarga ta'sir qilmasdan, qatlamlarning bir qismiga o'zgartirish kiritishni osonlashtiradi. Buning sababi shundaki, har bir layer erkin va mustaqil bog'langan, ya'ni bitta layerga kiritilgan o'zgarishlar butun tizimga ta'sir qilmaydi.

Bundan tashqari, toza arxitektura kodning qayta ishlatilishiga yordam beradi, chunki komponentlar muhim o'zgarishlar kiritmasdan turli loyihalar va ilovalarda qayta ishlatilishi mumkin.

Clean architektura, dasturchilarga har bir qatlamni alohida-alohida qilib osongina testlab ko'rishi va qatlamning qolgan qismiga ta'sir qilmasdan kerak bo'lganda funksionalliklarni qo'shish yoki o'chirish imkonini berish orqali testdan o'tkazish va kengayishni ta'minlaydi. Bundan tashqari, clean architektura kodning qayta ishlatilishiga yordam beradi.

<img width="924" alt="Screen Shot 2023-04-09 at 17 31 07" src="https://user-images.githubusercontent.com/77477995/230772618-f20f53d1-815f-49ab-861c-85d30f584842.png">

*2 - rasm. Clean architectura uchun Projectimizda ajratilgan qatlamlar tasvirlangan diagramma*

Bunda projectimiz qatlamlari 3 qismdan iborat
- UI layer
- Domain layer
- Data layer
- 
**```UI layer```** - UI qatlamining vazifasi dastur ma'lumotlarini ekranda ko'rsatishdir. Foydalanuvchi m-n Buttonni click qilish, EditTextda yozish, serverdan kelgan malumotlarni , foydalanuvchi interfeysi oʻzgarishlarni ko'rsatib berish vazifasini bajaradi. Demak bu qatlamda, biz UI ga tegishli bo'lgan logikalar bo'lishini taminlashimiz va qolgan logikalarni kerakli qatlamlarga joylashtirishimiz kerak.

**```Domain layer```** - UI va Data qatlamlari o'rtasidagi o'zaro aloqalarni soddalashtirish va qayta ishlatish uchun Biz Domen qatlamidan foydalanamiz. Va asosiy biznes logikalarni shu qatlamda bajaramiz.

**```Data layer```** - Bu qatlam Ilovamiz ma'lumotlarni qanday yaratishi, saqlashi va o'zgartirishini belgilaydigan qoidalardan iborat.
Data qatlami ko'p ma'lumot o'z ichiga olishi mumkin bo'lgan databasedan iborat bo'lishi yoki malumotlarni serverdan yuklash uchun network servicelari bo'lishi mumkin. Data qatlam ma'lumotlar logikasi bilan ishlash uchun Repositorylardan iborat bo'ladi, Ilovamizda ishlatadigan har bir turdagi ma'lumotlar uchun Repository classini yaratishimiz kerak. Masalan Userga bog'liq ma'lumotlar uchun UserRepository classini yaratishimiz mumkin.

**Xulosa!**
Umuman olganda, toza arxitektura ishlab chiquvchilarga moslashuvchan, kengaytiriladigan va o'zgaruvchan talablarga moslashtiriladigan yuqori sifatli, dasturiy ta'minotni yaratishga yordam beradi.

**Eslatma!**
Ushbu proektimda faqatgina layerlarga ajratilib, classlar qo'lda kiritilgan, bunda dependency injection frameworklaridan va design patternlar(Mvvm, Mvp...) dan foydalanilmagan, sababi clean architecturani tushunish uchun bu yo'lni tanlaganman, chunki dependencylarni qo'lda kiritish qanchalik noqulat va murakkablashib ketishini ko'rishimiz mumkin, bu yo'l orqali dependency injection texnologiyalarni yaxshi tushunib olishimizni nazarda tutganman, Bunda design patternlarning ham proektimizdagi o'rnini bilib olishimiz mumkin. Clean architectura uchun kerakli bo'lgan texnologiyalarni qadam va qadam github repositoryamdan ko'rishingiz mumkin. *


```kotlin 
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val authStorage: AuthStorage by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = requireContext())
    }

    private val saveAuthParamMapToStorage = SaveAuthenticationParamMapToStorage()
    private val authRequestMapToDomain = AuthenticationRequestMapToDomain()

    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(
            authStorage = authStorage,
            saveAuthParamMapToStorage = saveAuthParamMapToStorage,
            authRequestMapToDomain = authRequestMapToDomain
        )
    }
    private val getAuthUseCase: GetAuthUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetAuthUseCaseImpl(authRepository = authRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initClickView()
    }

    private fun initClickView() = binding.apply {
        btnLogin.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()
            val password = etPassword.text.toString()
            val loginParam = LoginParam(
                phoneNumber = phoneNumber,
                password = password
            )
            val success = getAuthUseCase(param = loginParam)
            if (success) Toast.makeText(requireContext(), R.string.success, Toast.LENGTH_SHORT).show()
            else Toast.makeText(requireContext(), R.string.failed, Toast.LENGTH_SHORT).show()
        }
}```
