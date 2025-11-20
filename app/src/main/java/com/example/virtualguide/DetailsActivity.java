package com.example.virtualguide;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualguide.data.KnowledgeRepository;
import com.example.virtualguide.model.KnowledgeCategory;
import com.example.virtualguide.model.KnowledgeItem;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM_ID = "extra_item_id";

    private ProgressBar pbIntensity;
    private ProgressBar pbComplexity;
    private ProgressBar pbStability;
    private TextView tvIntensityValue;
    private TextView tvComplexityValue;
    private TextView tvStabilityValue;

    private TextView tabBehavior;
    private TextView tabUseCases;
    private TextView tvProfileText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        View heroCard = findViewById(R.id.heroCard);

        TextView tvTitle = findViewById(R.id.tvDetailsTitle);
        TextView tvCategory = findViewById(R.id.tvDetailsCategory);
        TextView tvShort = findViewById(R.id.tvDetailsShort);
        TextView tvText = findViewById(R.id.tvDetailsText);

        TextView tvTagType = findViewById(R.id.tvTagType);
        TextView tvTagLevel = findViewById(R.id.tvTagLevel);
        TextView tvTagFocus = findViewById(R.id.tvTagFocus);

        pbIntensity = findViewById(R.id.pbIntensity);
        pbComplexity = findViewById(R.id.pbComplexity);
        pbStability = findViewById(R.id.pbStability);
        tvIntensityValue = findViewById(R.id.tvIntensityValue);
        tvComplexityValue = findViewById(R.id.tvComplexityValue);
        tvStabilityValue = findViewById(R.id.tvStabilityValue);

        tabBehavior = findViewById(R.id.tabBehavior);
        tabUseCases = findViewById(R.id.tabUseCases);
        tvProfileText = findViewById(R.id.tvProfileText);

        int id = getIntent().getIntExtra(EXTRA_ITEM_ID, -1);
        KnowledgeItem item = KnowledgeRepository.getById(id);

        if (item == null) {
            finish();
            return;
        }

        // Основной текст
        tvTitle.setText(item.getTitle());
        tvCategory.setText(item.getCategoryName());
        tvShort.setText(item.getShortDescription());
        tvText.setText(item.getFullDescription());

        // Чипы
        applyTagsForCategory(item.getCategory(), tvTagType, tvTagLevel, tvTagFocus);

        // Метрики
        fillMetricsForCategory(item.getCategory());

        // Профиль модуля (табы)
        setupProfileTabs(item.getCategory());

        // Анимация hero-карточки
        if (heroCard != null) {
            heroCard.setAlpha(0f);
            heroCard.setTranslationY(40f);
            ViewPropertyAnimator anim = heroCard.animate();
            anim.alpha(1f)
                    .translationY(0f)
                    .setDuration(500)
                    .start();
        }
    }

    private void applyTagsForCategory(KnowledgeCategory category,
                                      TextView tvType,
                                      TextView tvLevel,
                                      TextView tvFocus) {
        if (category == null) return;

        switch (category) {
            case CORE:
                tvType.setText("Тип: Понятие");
                tvLevel.setText("Уровень: Базовый");
                tvFocus.setText("Фокус: Среда");
                break;

            case TECH:
                tvType.setText("Тип: Технология");
                tvLevel.setText("Уровень: Технический");
                tvFocus.setText("Фокус: Инфраструктура");
                break;

            case PARTICIPANTS:
                tvType.setText("Тип: Участник");
                tvLevel.setText("Уровень: Поведенческий");
                tvFocus.setText("Фокус: Роль");
                break;

            case PROCESSES:
                tvType.setText("Тип: Процесс");
                tvLevel.setText("Уровень: Динамический");
                tvFocus.setText("Фокус: Сценарий");
                break;
        }
    }




    private void fillMetricsForCategory(KnowledgeCategory category) {
        int intensity = 60;
        int complexity = 60;
        int stability = 60;

        if (category != null) {
            switch (category) {
                case CORE:
                    intensity = 40;
                    complexity = 50;
                    stability = 80;
                    break;
                case TECH:
                    intensity = 75;
                    complexity = 70;
                    stability = 65;
                    break;
                case PARTICIPANTS:
                    intensity = 55;
                    complexity = 60;
                    stability = 50;
                    break;
                case PROCESSES:
                    intensity = 65;
                    complexity = 80;
                    stability = 70;
                    break;
            }
        }

        pbIntensity.setMax(100);
        pbComplexity.setMax(100);
        pbStability.setMax(100);

        pbIntensity.setProgress(intensity);
        pbComplexity.setProgress(complexity);
        pbStability.setProgress(stability);

        tvIntensityValue.setText(intensity + " %");
        tvComplexityValue.setText(complexity + " %");
        tvStabilityValue.setText(stability + " %");
    }

    private void setupProfileTabs(KnowledgeCategory category) {
        final String behaviorText;
        final String useCasesText;

        if (category == null) {
            behaviorText = "Поведение модуля описывается набором внутренних параметров и логикой реакции на цифровые стимулы.";
            useCasesText = "Модуль может использоваться для исследования реакции пользователей на различные конфигурации виртуальной среды.";
        } else {
            switch (category) {
                case CORE:
                    behaviorText =
                            "В концептуальных модулях фиксируются базовые принципы работы виртуальной среды. " +
                                    "Они задают, как распределяются объекты, какие связи между ними возможны и как меняется состояние при взаимодействии.";
                    useCasesText =
                            "• Описание структуры виртуальной исследовательской среды;\n" +
                                    "• Формирование терминологической базы проекта;\n" +
                                    "• Подготовка сценариев для последующих экспериментальных модулей.";
                    break;
                case TECH:
                    behaviorText =
                            "Технологические модули управляют обработкой сигналов и состоянием цифровой инфраструктуры. " +
                                    "Они регулируют интенсивность стимулов, скорость отклика системы и качество визуально-аудиального представления.";
                    useCasesText =
                            "• Настройка каналов подачи стимулов в эксперименте;\n" +
                                    "• Имитация отказов и перегрузок технических подсистем;\n" +
                                    "• Тестирование устойчивости среды при изменении параметров.";
                    break;
                case PARTICIPANTS:
                    behaviorText =
                            "Модули участников описывают роли, стратегии и типичные модели поведения пользователя в виртуальной среде. " +
                                    "Они фиксируют возможные действия, ограничения и характер реакции на изменения окружения.";
                    useCasesText =
                            "• Анализ поведенческих стратегий пользователей;\n" +
                                    "• Сравнение реакций разных групп участников;\n" +
                                    "• Подготовка рекомендаций по адаптации интерфейса под разные типы пользователей.";
                    break;
                case PROCESSES:
                    behaviorText =
                            "Процессные модули описывают ход эксперимента: последовательность этапов, точки сбора данных " +
                                    "и условия перехода между состояниями виртуальной среды.";
                    useCasesText =
                            "• Проектирование исследовательских сценариев;\n" +
                                    "• Анализ устойчивости процессов при изменении параметров;\n" +
                                    "• Документирование типовых исследовательских процедур.";
                    break;
                default:
                    behaviorText = "";
                    useCasesText = "";
                    break;
            }
        }

        // Изначально выбран таб "Поведение"
        selectBehaviorTab(behaviorText, useCasesText);

        tabBehavior.setOnClickListener(v -> selectBehaviorTab(behaviorText, useCasesText));
        tabUseCases.setOnClickListener(v -> selectUseCasesTab(behaviorText, useCasesText));
    }

    private void selectBehaviorTab(String behaviorText, String useCasesText) {
        tabBehavior.setBackgroundResource(R.drawable.btn_timer_primary);
        tabBehavior.setTextColor(getResources().getColor(android.R.color.white));

        tabUseCases.setBackgroundResource(R.drawable.btn_timer_secondary);
        tabUseCases.setTextColor(getResources().getColor(R.color.gradient_end));

        tvProfileText.setText(behaviorText);
    }

    private void selectUseCasesTab(String behaviorText, String useCasesText) {
        tabBehavior.setBackgroundResource(R.drawable.btn_timer_secondary);
        tabBehavior.setTextColor(getResources().getColor(R.color.gradient_end));

        tabUseCases.setBackgroundResource(R.drawable.btn_timer_primary);
        tabUseCases.setTextColor(getResources().getColor(android.R.color.white));

        tvProfileText.setText(useCasesText);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
