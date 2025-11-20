package com.example.virtualguide.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualguide.DetailsActivity;
import com.example.virtualguide.R;
import com.example.virtualguide.data.KnowledgeRepository;
import com.example.virtualguide.model.KnowledgeCategory;
import com.example.virtualguide.model.KnowledgeItem;
import com.google.android.material.chip.Chip;

import java.util.List;

public class HomeFragment extends Fragment {

    private KnowledgeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewHome);
        Chip chipAll = root.findViewById(R.id.chipAll);
        Chip chipCore = root.findViewById(R.id.chipCore);
        Chip chipTech = root.findViewById(R.id.chipTech);
        Chip chipParticipants = root.findViewById(R.id.chipParticipants);

        adapter = new KnowledgeAdapter(
                KnowledgeRepository.getAll(),
                item -> {
                    Intent intent = new Intent(requireContext(), DetailsActivity.class);
                    intent.putExtra(DetailsActivity.EXTRA_ITEM_ID, item.getId());
                    startActivity(intent);
                    requireActivity().overridePendingTransition(
                            R.anim.slide_in_right,
                            R.anim.slide_out_left
                    );
                }
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        // фильтры
        chipAll.setOnClickListener(v -> updateList(null));
        chipCore.setOnClickListener(v -> updateList(KnowledgeCategory.CORE));
        chipTech.setOnClickListener(v -> updateList(KnowledgeCategory.TECH));
        chipParticipants.setOnClickListener(v -> updateList(KnowledgeCategory.PARTICIPANTS));

        chipAll.setChecked(true);
        updateList(null);

        return root;
    }

    private void updateList(@Nullable KnowledgeCategory category) {
        List<KnowledgeItem> data = KnowledgeRepository.getByCategory(category);
        adapter.updateList(data);
    }
}
