from pathlib import Path
import re
from datetime import datetime

BASE_DIR = Path("src/main/java/nemesis/impl/module")
README_PATH = Path("README.md")


def collect_modules(base: Path):
    """Collect module categories and modules (folder → files)."""
    data = {}
    for category in sorted(base.iterdir()):
        if category.is_dir():
            category_name = category.name.capitalize()
            modules = [f.stem for f in category.glob("*.java")]
            data[category_name] = sorted(modules)
    return data


def generate_features(modules: dict):
    """Generate Markdown for the Features section with collapsible categories."""
    total = sum(len(v) for v in modules.values())
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M")

    lines = []
    lines.append("## Features\n")
    for category, mods in modules.items():
        lines.append(f"<details>\n")
        lines.append(f"<summary><b>{category} [{len(mods)}]</b></summary>\n\n")
        for mod in mods:
            lines.append(f"- {mod}\n")
        lines.append("</details>\n\n")

    lines.append(f"**Total Modules:** `{total}`  \n")
    lines.append(f"_Last updated: {timestamp}_\n")
    return "".join(lines)


def update_readme():
    if not BASE_DIR.exists():
        print(f"Error: Module directory not found: {BASE_DIR}")
        exit(1)

    if not README_PATH.exists():
        print("Error: README.md not found.")
        exit(1)

    modules = collect_modules(BASE_DIR)
    new_features = generate_features(modules)

    text = README_PATH.read_text(encoding="utf-8")

    # Remove any existing Features section
    features_pattern = r"(## Features[\s\S]*?)(?=\n## |\Z)"
    text_no_features = re.sub(features_pattern, "", text)

    # Insert Features section right after INFO
    info_pattern = r"(## INFO[\s\S]*?)(?=\n## |\Z)"
    if re.search(info_pattern, text_no_features):
        def insert_after_info(match):
            return match.group(0).rstrip() + "\n\n" + new_features.strip() + "\n\n"
        new_text = re.sub(info_pattern, insert_after_info, text_no_features)
        print("Inserted collapsible Features section after INFO.")
    else:
        new_text = text_no_features.strip() + "\n\n" + new_features.strip() + "\n\n"
        print("No INFO section found. Added collapsible Features section at the end.")

    README_PATH.write_text(new_text.strip() + "\n", encoding="utf-8")
    print(f"README.md updated successfully ({sum(len(v) for v in modules.values())} modules total).")


if __name__ == "__main__":
    update_readme()