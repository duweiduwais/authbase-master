package com.duwei.contract;

import com.duwei.commonsspringbootstarter.base.MenuResource;
import com.duwei.commonsspringbootstarter.vo.Menu;
import com.duwei.securityspringbootstarter.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ContractsController {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private AuthService authService;


    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("contracts/list")
    @MenuResource
    public String getContracstList(Model model){
        final List<Contract> contracts = contractRepository.findAll();
        List<Menu> menus =  authService.getMenusByAppname(applicationName);

        List<MenuContract> menuContracts = menus.stream()
                .map(m -> new MenuContract(m.getName(),contracts))
                .collect(Collectors.toList());
        model.addAttribute("contracts",menuContracts);
        return "contracts";
    }

    class MenuContract{
        private String menuName;

        private List<Contract> contracts;

        public MenuContract() {
        }

        public MenuContract(String menuName, List<Contract> contracts) {
            this.menuName = menuName;
            this.contracts = contracts;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public List<Contract> getContracts() {
            return contracts;
        }

        public void setContracts(List<Contract> contracts) {
            this.contracts = contracts;
        }
    }
}
